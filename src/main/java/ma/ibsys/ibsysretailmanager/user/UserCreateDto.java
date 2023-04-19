package ma.ibsys.ibsysretailmanager.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCreateDto {
  @Size(min = 2, max = 50, message = "firstName must be between {min} and {max} characters long")
  @NotBlank(message = "firstName is mandatory")
  private String firstName;

  @Size(min = 2, max = 50, message = "lastName must be between {min} and {max} characters long")
  @NotBlank(message = "lastName is mandatory")
  private String lastName;

  @Email(message = "email should be valid")
  @NotBlank(message = "email is mandatory")
  private String email;

  @Size(min = 8, message = "password must be between {min} and {max} characters long")
  @NotBlank(message = "password is mandatory")
  private String password;

  @NotNull(message = "role is mandatory")
  private Role role;
}
