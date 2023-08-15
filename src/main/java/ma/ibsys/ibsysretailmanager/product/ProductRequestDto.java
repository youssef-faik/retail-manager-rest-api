package ma.ibsys.ibsysretailmanager.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
        title = "Product Request Schema",
        description = "Request body for creating/updating a product.")
public class ProductRequestDto {
  @Schema(description = "Product barcode.", example = "1234567890", required = true)
  @NotBlank(message = "Barcode is required")
  private String barCode;

  @Schema(description = "Product name.", example = "Laptop", required = true)
  @Size(min = 2, max = 1000, message = "Name must be between {min} and {max} characters.")
  @NotBlank(message = "Name is required")
  private String name;

  @Schema(
          description = "Selling price of the product excluding tax.",
          example = "12999.99",
          required = true)
  @NotBlank(message = "Selling price excluding tax is required.")
  @Positive(message = "Selling price must be a positive number.")
  private BigDecimal sellingPriceExcludingTax;

  @Schema(description = "Purchase price of the product.", example = "10000.99", required = true)
  @NotNull(message = "Purchase price is required.")
  @Positive(message = "Purchase price must be a positive number.")
  private BigDecimal purchasePrice;

  @Schema(description = "Tax rate of the product.", example = "TEN", required = true)
  @NotBlank(message = "Tax rate is required.")
  private TaxRate taxRate;

  @Schema(description = "ID of the product's category.", example = "1", required = true)
  @NotNull(message = "Category ID is required")
  private int category;
}
