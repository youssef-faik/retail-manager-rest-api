package ma.ibsys.ibsysretailmanager.security.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(
    title = "Schéma de la réponse d'authentification",
    description = "Corps de la réponse d'authentification de l'utilisateur")
public class AuthenticationResponse {
  @Schema(description = "Jeton JWT", example = "eyJhbGciOiJIUzI1NiJ9...")
  private String token;

  @Schema(description = "ID de l'utilisateur", example = "1")
  private Long id;

  @Schema(description = "Prénom", example = "Youssef")
  private String firstName;

  @Schema(description = "Nom", example = "Faik")
  private String lastName;

  @Schema(description = "Email", example = "yusef@mail.com")
  private String email;

  @Schema(description = "Rôle de l'utilisateur", example = "ADMIN")
  private String role;
}
