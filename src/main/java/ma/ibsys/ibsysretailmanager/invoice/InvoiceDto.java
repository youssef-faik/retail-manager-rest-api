package ma.ibsys.ibsysretailmanager.invoice;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ibsys.ibsysretailmanager.customer.CustomerResponseDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
    title = "Schéma de réponse de facture.",
    description = "Corps de la réponse pour une facture.")
public class InvoiceDto {
  @Schema(description = "L'ID de la facture")
  private int id;

  @Schema(description = "La date à laquelle la facture a été émise.")
  private LocalDate issueDate;

  @Schema(description = "Le client auquel la facture est émise.")
  private CustomerResponseDto customer;

  @Schema(description = "La liste des articles et de leurs quantités inclus dans la facture.")
  private List<InvoiceItemResponseDto> items;
}
