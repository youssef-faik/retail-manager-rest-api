package ma.ibsys.ibsysretailmanager.security.auth;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ibsys.ibsysretailmanager.user.Role;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
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
  
  @NotBlank(message = "role is mandatory")
  private String role;
}
