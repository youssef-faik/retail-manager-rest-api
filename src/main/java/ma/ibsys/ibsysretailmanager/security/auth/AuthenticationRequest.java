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
    description = "Request body for user authentication")
public class AuthenticationRequest {
  @Schema(description = "User email", example = "yusef@mail.com")
  @Email(message = "email should be valid")
  @NotBlank(message = "email is mandatory")
  private String email;

  @Schema(description = "User password", example = "secret-password")
  @NotBlank(message = "password is mandatory")
  private String password;
}
