package ma.ibsys.ibsysretailmanager.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
    title = "Schéma de réponse utilisateur",
    description = "Corps de réponse pour les détails de l'utilisateur.")
public class UserDto {
  @Schema(description = "ID de l'utilisateur.", example = "2")
  private int id;

  @Schema(description = "Prénom de l'utilisateur.", example = "Said")
  private String firstName;

  @Schema(description = "Nom de famille de l'utilisateur.", example = "Alami")
  private String lastName;

  @Schema(description = "Adresse e-mail de l'utilisateur.", example = "said.alami@example.com")
  private String email;

  @Schema(description = "Rôle de l'utilisateur.", example = "MANAGER")
  private Role role;
}
