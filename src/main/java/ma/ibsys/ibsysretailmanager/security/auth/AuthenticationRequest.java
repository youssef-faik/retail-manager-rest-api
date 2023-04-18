package ma.ibsys.ibsysretailmanager.security.auth;

import jakarta.persistence.Column;
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
public class AuthenticationRequest {
  @Email(message = "email should be valid")
  @NotBlank(message = "email is mandatory")
  private String email;
  
  @NotBlank(message = "password is mandatory")
  private String password;
}
