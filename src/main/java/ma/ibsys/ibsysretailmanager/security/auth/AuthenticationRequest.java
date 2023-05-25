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
    title = "Schéma de la demande d'authentification.",
    description = "Corps de la demande d'authentification de l'utilisateur.")
public class AuthenticationRequest {
  @Schema(description = "Email de l'utilisateur.", example = "yusef@mail.com")
  @Email(message = "l'email doit être valide.")
  @NotBlank(message = "l'email est obligatoire.")
  private String email;

  @Schema(description = "Mot de passe de l'utilisateur.", example = "secret-password")
  @NotBlank(message = "le mot de passe est obligatoire.")
  private String password;
}
