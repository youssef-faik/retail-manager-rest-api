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
    title = "Configuration Parameter Schema",
    description = "Represents a configuration parameter.")
public class ConfigOptionDto {
  @Schema(description = "Parameter key.", example = "LAST_INVOICE_NUMBER", required = true)
  @NotBlank(message = "Key is required.")
  private ConfigKey key;

  @Schema(description = "Parameter value.", example = "12", required = true)
  @NotBlank(message = "Value is required.")
  private String value;
}
