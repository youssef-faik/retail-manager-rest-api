package ma.ibsys.ibsysretailmanager.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import ma.ibsys.ibsysretailmanager.security.token.TokenRepository;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private static final String AUTHORIZATION_HEADER = "Authorization";
  private static final String TOKEN_PREFIX = "Bearer ";
  private final JwtService jwtService;
  private final UserDetailsService userDetailsService;
  private final TokenRepository tokenRepository;
  
  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain)
      throws ServletException, IOException {

    // Get the Authorization header from the HTTP request.
    final String authHeader = request.getHeader(AUTHORIZATION_HEADER);

    // If the Authorization header is not present or does not start with "Bearer ",
    // pass the request to the next filter in the chain.
    if (authHeader == null || !authHeader.startsWith(TOKEN_PREFIX)) {
      filterChain.doFilter(request, response);
      return;
    }

    // Extract the JWT token from the Authorization header.
    final String jwt = authHeader.substring(7);

    // Extract the email from the JWT token using the JwtService.
    final String userEmail = jwtService.extractUsername(jwt);

    // If the email is not null and the authentication context is not already set,
    // perform the JWT authentication and authorization.
    if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

      // Load the user details from the UserDetailsService using the email.
      UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

      // Check if the token is valid and not revoked or expired using the TokenRepository.
      var isTokenValid =
          tokenRepository.findByToken(jwt).map(t -> !t.isExpired() && !t.isRevoked()).orElse(false);

      // If the token is valid and the user details are not null,
      // create and set the authentication token in the SecurityContext.
      if (jwtService.isTokenValid(jwt, userDetails) && isTokenValid) {
        UsernamePasswordAuthenticationToken authToken =
            new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());

        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }

    // Pass the request to the next filter in the chain.
    filterChain.doFilter(request, response);
  }
}
