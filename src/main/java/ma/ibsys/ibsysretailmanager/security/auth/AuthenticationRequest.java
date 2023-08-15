package ma.ibsys.ibsysretailmanager.security.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        title = "Authentication Request Schema",
        description = "Request body for user authentication.")
public class AuthenticationRequest {
  @Schema(description = "User's email.", example = "admin@mail.com")
  @Email(message = "Email must be valid.")
  @NotBlank(message = "Email is required.")
  private String email;

  @Schema(description = "User's password.", example = "secret-password")
  @NotBlank(message = "Password is required.")
  private String password;
}
