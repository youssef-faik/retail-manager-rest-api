package ma.ibsys.ibsysretailmanager.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
    title = "Schéma de requête de création d'utilisateur.",
    description = "Corps de la requête pour créer un nouvel utilisateur.")
public class UserCreateDto {
  @Schema(description = "Prénom de l'utilisateur.", required = true, example = "Said")
  @Size(min = 2, max = 50, message = "Le prénom doit comporter entre {min} et {max} caractères.")
  @NotBlank(message = "Le prénom est obligatoire.")
  private String firstName;

  @Schema(description = "Le nom de l'utilisateur.", required = true, example = "Alami")
  @Size(min = 2, max = 50, message = "Le nom doit comporter entre {min} et {max} caractères.")
  @NotBlank(message = "Le nom est obligatoire.")
  private String lastName;

  @Schema(
      description = "Adresse e-mail de l'utilisateur.",
      required = true,
      example = "said.alami@example.com")
  @Email(message = "Adresse e-mail doit être valide.")
  @NotBlank(message = "Adresse e-mail est obligatoire.")
  private String email;

  @Schema(description = "Mot de passe de l'utilisateur.", required = true, example = "mot-de-passe")
  @Size(min = 8, message = "Le mot de passe doit comporter au moins {min} caractères.")
  @NotBlank(message = "Mot de passe est obligatoire.")
  private String password;

  @Schema(description = "Rôle de l'utilisateur.", required = true, example = "MANAGER")
  @NotNull(message = "Rôle est obligatoire.")
  private Role role;
}
