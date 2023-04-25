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
@Schema(title = "Product Response Schema", description = "Response body for retrieving a product")
public class ProductResponseDto {
  @Schema(description = "ID of the product", example = "1")
  private int id;

  @Schema(description = "Barcode of the product", example = "1234567890")
  private String barCode;

  @Schema(description = "Name of the product", example = "Laptop")
  private String name;

  @Schema(description = "Price of the product excluding tax", example = "12999.99")
  private BigDecimal priceExcludingTax;

  @Schema(description = "Tax rate of the product", example = "TEN")
  private TaxRate taxRate;
}
