package ma.ibsys.ibsysretailmanager.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
    title = "Schéma de demande de modification du mot de passe.",
    description = "Corps de la requête pour changer le mot de passe.")
public class ChangePasswordRequest {

  @Schema(description = "Ancien mot de passe de l'utilisateur.", required = true)
  @Size(min = 8, message = "L'ancien mot de passe doit contenir entre {min} et {max} caractères.")
  @NotBlank(message = "oldPassword est obligatoire.")
  private String oldPassword;

  @Schema(description = "Nouveau mot de passe de l'utilisateur.", required = true)
  @Size(min = 8, message = "Le nouveau mot de passe doit contenir entre {min} et {max} caractères.")
  @NotBlank(message = "newPassword est obligatoire.")
  private String newPassword;
}
