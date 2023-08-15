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
@Schema(
        title = "Authentication Response Schema",
        description = "Response body for user authentication.")
public class AuthenticationResponse {
  @Schema(description = "JWT Token", example = "eyJhbGciOiJIUzI1NiJ9...")
  private String token;

  @Schema(description = "User ID", example = "1")
  private Long id;

  @Schema(description = "First Name", example = "Youssef")
  private String firstName;

  @Schema(description = "Last Name", example = "Faik")
  private String lastName;

  @Schema(description = "Email", example = "yusef@mail.com")
  private String email;

  @Schema(description = "User Role", example = "ADMIN")
  private String role;
}
