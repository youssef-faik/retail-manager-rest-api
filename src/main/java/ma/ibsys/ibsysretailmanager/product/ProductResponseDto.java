package ma.ibsys.ibsysretailmanager.product;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
    title = "Schéma de réponse de produit.",
    description = "Corps de réponse pour la récupération d'un produit.")
public class ProductResponseDto {
  @Schema(description = "ID du produit.", example = "1")
  private int id;

  @Schema(description = "Code-barres du produit.", example = "1234567890")
  private String barCode;

  @Schema(description = "Nom du produit.", example = "Ordinateur portable")
  private String name;

  @Schema(description = "Prix de vente du produit hors taxe.", example = "12999.99")
  private BigDecimal sellingPriceExcludingTax;

  @Schema(description = "Prix d'achat du produit.", example = "10000.99")
  private BigDecimal purchasePrice;

  @Schema(description = "Taux de taxe du produit.", example = "TEN")
  private TaxRate taxRate;
}
