package ma.ibsys.ibsysretailmanager.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        title = "User Creation Request Schema",
        description = "Request body for creating a new user.")
public class UserCreateDto {
  @Schema(description = "User's first name.", required = true, example = "Said")
  @Size(min = 2, max = 50, message = "First name must be between {min} and {max} characters.")
  @NotBlank(message = "First name is required.")
  private String firstName;

  @Schema(description = "User's last name.", required = true, example = "Alami")
  @Size(min = 2, max = 50, message = "Last name must be between {min} and {max} characters.")
  @NotBlank(message = "Last name is required.")
  private String lastName;

  @Schema(
          description = "User's email address.",
          required = true,
          example = "said.alami@example.com")
  @Email(message = "Email address must be valid.")
  @NotBlank(message = "Email address is required.")
  private String email;

  @Schema(description = "User's password.", required = true, example = "password")
  @Size(min = 8, message = "Password must be at least {min} characters long.")
  @NotBlank(message = "Password is required.")
  private String password;

  @Schema(description = "User's role.", required = true, example = "MANAGER")
  @NotNull(message = "Role is required.")
  private Role role;
}
