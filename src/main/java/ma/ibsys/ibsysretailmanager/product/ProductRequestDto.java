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
@Schema(title = "Product Request Schema", description = "Request body for creating/updating a product")
public class ProductRequestDto {
  @Schema(description = "Barcode of the product", example = "1234567890", required = true)
  @NotBlank(message = "barCode is mandatory")
  private String barCode;

  @Schema(description = "Name of the product", example = "Laptop", required = true)
  @Size(min = 2, max = 1000, message = "name must be between {min} and {max} characters long")
  @NotBlank(message = "name is mandatory")
  @Column(name = "name", nullable = false)
  private String name;

  @Schema(description = "Selling Price of the product excluding tax", example = "12999.99", required = true)
  @NotBlank(message = "sellingPriceExcludingTax is mandatory")
  @Positive(message = "sellingPriceExcludingTax must be a positive number")
  private BigDecimal sellingPriceExcludingTax;
  
  @Schema(description = "Purchase Price of the product", example = "10000.99", required = true)
  @NotNull(message = "purchasePrice is mandatory")
  @Positive(message = "priceExcludingTax must be a positive number")
  private BigDecimal purchasePrice;

  @Schema(description = "Tax rate of the product", example = "Ten", required = true)
  @NotBlank(message = "taxRate is mandatory")
  private TaxRate taxRate;
}
