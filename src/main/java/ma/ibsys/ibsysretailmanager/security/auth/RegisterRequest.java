package ma.ibsys.ibsysretailmanager.security.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
  @Size(min = 2, max = 50, message = "First name must be between {min} and {max} characters.")
  @NotBlank(message = "First name is required.")
  private String firstName;

  @Size(min = 2, max = 50, message = "Last name must be between {min} and {max} characters.")
  @NotBlank(message = "Last name is required.")
  private String lastName;

  @Email(message = "Email must be valid.")
  @NotBlank(message = "Email is required.")
  private String email;

  @Size(min = 8, message = "Password must be at least {min} characters.")
  @NotBlank(message = "Password is required.")
  private String password;

  @NotBlank(message = "User role is required.")
  private String role;
}
