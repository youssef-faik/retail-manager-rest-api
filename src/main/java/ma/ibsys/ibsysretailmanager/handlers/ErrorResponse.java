package ma.ibsys.ibsysretailmanager.handlers;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(
    title = "Schéma de réponse d'erreur",
    description = "Représente la réponse d'erreur renvoyée par l'API.")
public class ErrorResponse {
  @Schema(description = "Le code de statut HTTP associé à l'erreur.", example = "400")
  private HttpStatus httpStatus;

  @Schema(description = "L'horodatage de l'erreur.", example = "2023-04-26T10:30:00")
  private LocalDateTime timestamp;

  @Schema(description = "Le message d'erreur.", example = "L'entité demandée n'a pas été trouvée.")
  private String message;
}
