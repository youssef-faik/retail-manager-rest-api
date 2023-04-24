package ma.ibsys.ibsysretailmanager.security.auth;

import lombok.RequiredArgsConstructor;
import ma.ibsys.ibsysretailmanager.security.jwt.JwtService;
import ma.ibsys.ibsysretailmanager.security.token.Token;
import ma.ibsys.ibsysretailmanager.security.token.TokenRepository;
import ma.ibsys.ibsysretailmanager.security.token.TokenType;
import ma.ibsys.ibsysretailmanager.user.Role;
import ma.ibsys.ibsysretailmanager.user.User;
import ma.ibsys.ibsysretailmanager.user.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * This service class handles user authentication and registration using the UserRepository,
 * TokenRepository, PasswordEncoder, JwtService and AuthenticationManager. It provides methods for
 * registering a new user, authenticating an existing user and saving/retrieving authentication
 * tokens from the database.
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  /**
   * Registers a new user with the provided user information.
   *
   * @param request the request containing the user information
   * @return a UserDTO representing the registered user
   */
  public AuthenticationResponse register(RegisterRequest request) {
    var user =
        User.builder()
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(Role.valueOf(Role.class, request.getRole()))
            .build();

    var savedUser = repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    saveUserToken(savedUser, jwtToken);

    return AuthenticationResponse.builder()
        .id((long) savedUser.getId())
        .firstName(savedUser.getFirstName())
        .lastName(savedUser.getLastName())
        .email(savedUser.getEmail())
        .role(savedUser.getRole().name())
        .build();
  }

  /**
   * Authenticates an existing user with the provided email and password.
   *
   * @param request the request containing the email and password
   * @return an AuthenticationResponse containing the authentication token and user information
   */
  public ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    var user = repository.findByEmail(request.getEmail()).orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    AuthenticationResponse authenticationResponse =
        AuthenticationResponse.builder()
            .token(jwtToken)
            .id((long) user.getId())
            .email(user.getEmail())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .role(user.getRole().name())
            .build();
    return ResponseEntity.ok(authenticationResponse);
  }

  /**
   * Saves a new authentication token for the specified user.
   *
   * @param user the user for whom the token is being saved
   * @param jwtToken the token to save
   */
  private void saveUserToken(User user, String jwtToken) {
    var token =
        Token.builder()
            .user(user)
            .token(jwtToken)
            .tokenType(TokenType.BEARER)
            .expired(false)
            .revoked(false)
            .build();
    tokenRepository.save(token);
  }

  /**
   * Revokes all authentication tokens for the specified user.
   *
   * @param user the user for whom the tokens are being revoked
   */
  private void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser((long) user.getId());
    if (validUserTokens.isEmpty()) {
      return;
    }
    validUserTokens.forEach(
        token -> {
          token.setExpired(true);
          token.setRevoked(true);
        });
    tokenRepository.saveAll(validUserTokens);
  }
}
