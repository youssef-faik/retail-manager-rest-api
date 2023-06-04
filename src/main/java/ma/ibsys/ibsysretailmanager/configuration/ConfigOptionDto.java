package ma.ibsys.ibsysretailmanager.configuration;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(
    title = "Schéma d'un paramètre de configuration.",
    description = "Représente un paramètre de configuration.")
public class ConfigOptionDto {
  @Schema(description = "Clé du paramètre.", example = "LAST_INVOICE_NUMBER", required = true)
  @NotBlank(message = "La clé est obligatoire.")
  private ConfigKey key;

  @Schema(description = "Valeur du paramètre.", example = "12", required = true)
  @NotBlank(message = "La valeur est obligatoire.")
  private String value;
}
