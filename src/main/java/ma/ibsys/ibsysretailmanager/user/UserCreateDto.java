package ma.ibsys.ibsysretailmanager.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(title = "User Request Schema", description = "Request body for creating a new user.")
public class UserCreateDto {
  @Schema(description = "First name of the user.", required = true, example = "Said")
  @Size(min = 2, max = 50, message = "firstName must be between {min} and {max} characters long")
  @NotBlank(message = "firstName is mandatory")
  private String firstName;
  
  @Schema(description = "Last name of the user.", required = true, example = "Alami")
  @Size(min = 2, max = 50, message = "lastName must be between {min} and {max} characters long")
  @NotBlank(message = "lastName is mandatory")
  private String lastName;
  
  @Schema(description = "Email address of the user.", required = true, example = "said.alami@example.com")
  @Email(message = "email should be valid")
  @NotBlank(message = "email is mandatory")
  private String email;
  
  @Schema(description = "Password of the user.", required = true, example = "my-password")
  @Size(min = 8, message = "password must be between {min} and {max} characters long")
  @NotBlank(message = "password is mandatory")
  private String password;
  
  @Schema(description = "Role of the user.", required = true, example = "MANAGER")
  @NotNull(message = "role is mandatory")
  private Role role;
}
