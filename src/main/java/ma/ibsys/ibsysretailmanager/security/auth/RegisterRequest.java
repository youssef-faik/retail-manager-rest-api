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
  @Size(min = 2, max = 50, message = "Le prénom doit contenir entre {min} et {max} caractères.")
  @NotBlank(message = "Le prénom est obligatoire.")
  private String firstName;

  @Size(min = 2, max = 50, message = "Le nom doit contenir entre {min} et {max} caractères.")
  @NotBlank(message = "Le nom est obligatoire.")
  private String lastName;

  @Email(message = "L'email doit être valide.")
  @NotBlank(message = "L'email est obligatoire.")
  private String email;

  @Size(min = 8, message = "Le mot de passe doit contenir entre {min} et {max} caractères.")
  @NotBlank(message = "Le mot de passe est obligatoire.")
  private String password;

  @NotBlank(message = "Le rôle de l'utilisateur est obligatoire.")
  private String role;
}
