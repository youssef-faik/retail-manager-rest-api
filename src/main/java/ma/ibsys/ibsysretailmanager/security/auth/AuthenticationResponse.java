package ma.ibsys.ibsysretailmanager.security.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "Authentication Response Schema", description = "Response body for user authentication response")
public class AuthenticationResponse {
  @Schema(description = "JWT token", example = "eyJhbGciOiJIUzI1NiJ9...")
  private String token;

  @Schema(description = "User ID", example = "1")
  private Long id;

  @Schema(description = "First name", example = "Youssef")
  private String firstName;

  @Schema(description = "Last name", example = "Faik")
  private String lastName;

  @Schema(description = "Email", example = "yusef@mail.com")
  private String email;

  @Schema(description = "User role", example = "ADMIN")
  private String role;
}
