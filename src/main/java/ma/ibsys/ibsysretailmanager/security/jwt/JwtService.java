package ma.ibsys.ibsysretailmanager.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Service class for generating and validating JWT tokens.
 */
@Service
public class JwtService {
  /**
   * The secret key used for signing and verifying the JWT tokens.
   */
  private static final String SECRET_KEY = "5468576D5A7134743777217A25432A462D4A404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970337336763979244226452948404D";
  private static final long EXPIRATION_TIME_IN_SECONDS = 86_400; // 1 day
  
  /**
   * Extracts the username from the JWT token.
   *
   * @param token The JWT token string.
   * @return The username extracted from the token.
   */
  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }
  
  /**
   * Extracts a claim from the JWT token.
   *
   * @param token         The JWT token string.
   * @param claimsResolver The function used to extract the claim from the token's claims.
   * @param <T>           The type of the claim being extracted.
   * @return The extracted claim value.
   */
  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }
  
  /**
   * Extracts all claims from the JWT token.
   *
   * @param token The JWT token string.
   * @return A Claims object containing all claims extracted from the token.
   */
  private Claims extractAllClaims(String token) {
    return Jwts
            .parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
  }
  
  /**
   * Generates a JWT token for the given user details.
   *
   * @param userDetails The UserDetails object representing the authenticated user.
   * @return The generated JWT token string.
   */
  public String generateToken(UserDetails userDetails) {
    return generateToken(new HashMap<>(), userDetails);
  }
  
  /**
   * Generates a JWT token for the given user details and extra claims.
   *
   * @param extraClaims  A map of extra claims to include in the token.
   * @param userDetails The UserDetails object representing the authenticated user.
   * @return The generated JWT token string.
   */
  public String generateToken(
          Map<String, Object> extraClaims,
          UserDetails userDetails
  ) {
    return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_IN_SECONDS))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact();
  }
  
  /**
   * Gets the signing key used for signing and verifying JWT tokens.
   *
   * @return The signing key as a Key object.
   */
  private Key getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }
  
  /**
   * Validates that the given JWT token is valid for the given user details.
   *
   * @param token        The JWT token string to validate.
   * @param userDetails The UserDetails object representing the authenticated user.
   * @return true if the token is valid for the user details, false otherwise.
   */
  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
  }
  
  /**
   * Extracts the expiration date of the JWT token.
   *
   * @param token The JWT token to extract the expiration date from.
   * @return The expiration date of the JWT token.
   */
  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }
  
  /**
   * Checks if the JWT token is expired.
   *
   * @param token The JWT token to check for expiration.
   * @return true if the JWT token is expired, false otherwise.
   */
  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }
  
}
