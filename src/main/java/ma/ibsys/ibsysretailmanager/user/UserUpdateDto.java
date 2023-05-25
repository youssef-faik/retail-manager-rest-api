package ma.ibsys.ibsysretailmanager.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
    title = "Schéma de demande de mise à jour d'utilisateur",
    description = "Corps de la demande de mise à jour d'un utilisateur existant.")
public class UserUpdateDto {
  @Schema(description = "Prénom de l'utilisateur.", required = true, example = "Said")
  @Size(min = 2, max = 50, message = "Le prénom doit contenir entre {min} et {max} caractères.")
  @NotBlank(message = "Le prénom est obligatoire.")
  private String firstName;

  @Schema(description = "Nom de famille de l'utilisateur.", required = true, example = "Alami")
  @Size(
      min = 2,
      max = 50,
      message = "Le nom de famille doit contenir entre {min} et {max} caractères.")
  @NotBlank(message = "Le nom de famille est obligatoire.")
  private String lastName;

  @Schema(
      description = "Adresse e-mail de l'utilisateur.",
      required = true,
      example = "said.alami@example.com")
  @Email(message = "L'adresse e-mail doit être valide.")
  @NotBlank(message = "L'adresse e-mail est obligatoire.")
  private String email;

  @Schema(description = "Rôle de l'utilisateur.", required = true, example = "MANAGER")
  @NotNull(message = "Le rôle est obligatoire.")
  private Role role;
}
