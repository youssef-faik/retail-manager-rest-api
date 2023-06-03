package ma.ibsys.ibsysretailmanager.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
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
    title = "Schéma de demande de produit.",
    description = "Corps de la demande pour la création/mise à jour d'un produit.")
public class ProductRequestDto {
  @Schema(description = "Code-barres du produit.", example = "1234567890", required = true)
  @NotBlank(message = "Le code-barres est obligatoire")
  private String barCode;

  @Schema(description = "Nom du produit.", example = "Ordinateur portable", required = true)
  @Size(min = 2, max = 1000, message = "Le nom doit comporter entre {min} et {max} caractères.")
  @NotBlank(message = "Le nom est obligatoire")
  @Column(name = "name", nullable = false)
  private String name;

  @Schema(
      description = "Prix de vente du produit hors taxe.",
      example = "12999.99",
      required = true)
  @NotBlank(message = "Le prix de vente hors taxe est obligatoire.")
  @Positive(message = "Le prix de vente hors taxe doit être un nombre positif.")
  private BigDecimal sellingPriceExcludingTax;

  @Schema(description = "Prix d'achat du produit.", example = "10000.99", required = true)
  @NotNull(message = "Le prix d'achat est obligatoire.")
  @Positive(message = "Le prix d'achat doit être un nombre positif et superior a zero.")
  private BigDecimal purchasePrice;

  @Schema(description = "Taux de taxe du produit.", example = "TEN", required = true)
  @NotBlank(message = "Le taux de taxe est obligatoire.")
  private TaxRate taxRate;

  @Schema(description = "ID de la catégorie du produit.", example = "1", required = true)
  @NotNull(message = "ID de la catégorie est obligatoire")
  private int category;
}
