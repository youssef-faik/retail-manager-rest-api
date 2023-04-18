package ma.ibsys.ibsysretailmanager.security.config;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import ma.ibsys.ibsysretailmanager.security.token.TokenRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {
  private final TokenRepository tokenRepository;
  
  // Implementation of the logout method from LogoutHandler interface
  @Override
  public void logout(
          HttpServletRequest request,
          HttpServletResponse response,
          Authentication authentication
  ) {
    // Get the Authorization header from the HTTP request
    final String authHeader = request.getHeader("Authorization");
    
    // If the Authorization header is missing or does not start with "Bearer ",
    // return without doing anything
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      return;
    }
    
    // Extract the JWT token from the Authorization header
    final String jwt = authHeader.substring(7);
    
    // Try to find a stored token in the database that matches the extracted token
    var storedToken = tokenRepository.findByToken(jwt)
                                     .orElse(null);
    
    // If a matching token is found, do the following:
      // 1. mark it as expired and revoked,
      // 2. save the changes to the database
      // 3. clear the security context to log out the user
    if (storedToken != null) {
      storedToken.setExpired(true);
      storedToken.setRevoked(true);
      tokenRepository.save(storedToken);
      SecurityContextHolder.clearContext();
    }
  }
  
}
