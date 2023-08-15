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
@Schema(description = "Request schema for cart item.")
public class CartItemRequestDto {
  @NotNull(message = "Cart ID is required.")
  @Pattern(regexp = "^\\d{7}$", message = "Invalid cart ID format.")
  @Schema(description = "Cart ID.", example = "1234567")
  private String cartId;

  @NotNull(message = "Barcode is required.")
  @Pattern(regexp = "^\\d*$", message = "Invalid barcode format.")
  @Schema(description = "Product barcode.", example = "1234567890")
  private String barcode;
}
