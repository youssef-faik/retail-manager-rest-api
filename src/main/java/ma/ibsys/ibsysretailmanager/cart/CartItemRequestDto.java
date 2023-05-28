package ma.ibsys.ibsysretailmanager.cart;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
@Schema(description = "Schéma de demande pour l'élément du panier.")
public class CartItemRequestDto {
  @NotNull(message = "L'ID du panier est requis.")
  @Pattern(regexp = "^\\d{7}$", message = "Format d'ID du panier invalide.")
  @Schema(description = "L'ID du panier.", example = "1234567")
  private String cartId;

  @NotNull(message = "Le code-barres est requis.")
  @Pattern(regexp = "^\\d*$", message = "Format de code-barres invalide.")
  @Schema(description = "Le code-barres du produit.", example = "1234567890")
  private String barcode;
}
