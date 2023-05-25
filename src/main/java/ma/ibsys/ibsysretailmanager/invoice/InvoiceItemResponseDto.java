package ma.ibsys.ibsysretailmanager.invoice;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ibsys.ibsysretailmanager.product.ProductResponseDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
    title = "Schéma de réponse des éléments de facture.",
    description = "Représente un article inclus dans une facture.")
public class InvoiceItemResponseDto {
  @Schema(description = "Le produit facturé.", implementation = ProductResponseDto.class)
  private ProductResponseDto product;

  @Schema(description = "La quantité du produit facturé.")
  private Integer quantity;

  @Schema(description = "Le prix unitaire du produit facturé.")
  private BigDecimal unitPrice;
}
