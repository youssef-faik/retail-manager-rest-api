package ma.ibsys.ibsysretailmanager.invoice;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
    title = "Schéma des éléments de facture.",
    description = "Représente un article inclus dans une facture.")
public class InvoiceItemDto {
  @Schema(description = "L'ID du produit facturé.")
  @NotNull(message = "L'ID du produit ne peut pas être null.")
  private Integer productId;

  @Schema(description = "La quantité du produit facturé.")
  @NotNull(message = "La quantité ne peut pas être null.")
  @Min(value = 1, message = "La quantité doit être d'au moins 1.")
  private Integer quantity;

  @Schema(description = "Le prix unitaire du produit facturé.")
  @NotNull(message = "Le prix unitaire ne peut pas être null.")
  @DecimalMin(value = "1", message = "Le prix unitaire doit être supérieur ou égal à {value}.")
  private BigDecimal unitPrice;
}
