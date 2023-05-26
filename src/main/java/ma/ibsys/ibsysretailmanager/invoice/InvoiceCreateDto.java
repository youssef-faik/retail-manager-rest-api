package ma.ibsys.ibsysretailmanager.invoice;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
    title = "Schéma de demande de facture.",
    description = "Corps de la demande pour créer une facture.")
public class InvoiceCreateDto {
  @Schema(
      description = "Le numéro ICE du client auquel la facture est émise.",
      example = "563456789123456")
  @NotNull(message = "customerICE ne peut pas être null.")
  private String customerICE;

  @Schema(description = "La liste des articles et de leurs quantités à inclure dans la facture.")
  @NotEmpty(message = "La liste des articles ne peut pas être vide.")
  private List<@Valid InvoiceItemDto> items;
}
