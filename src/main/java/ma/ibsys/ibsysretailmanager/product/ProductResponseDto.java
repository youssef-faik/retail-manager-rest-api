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
        title = "Product Response Schema",
        description = "Response body for retrieving a product.")
public class ProductResponseDto {
  @Schema(description = "Product ID.", example = "1")
  private int id;

  @Schema(description = "Product barcode.", example = "1234567890")
  private String barCode;

  @Schema(description = "Product name.", example = "Laptop")
  private String name;

  @Schema(description = "Selling price of the product excluding tax.", example = "12999.99")
  private BigDecimal sellingPriceExcludingTax;

  @Schema(description = "Purchase price of the product.", example = "10000.99")
  private BigDecimal purchasePrice;

  @Schema(description = "Tax rate of the product.", example = "TEN")
  private TaxRate taxRate;

  @Schema(description = "ID of the product's category.", example = "1")
  private int category;
}
