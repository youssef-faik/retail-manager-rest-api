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
        title = "Invoice Item Response Schema",
        description = "Represents an item included in an invoice.")
public class InvoiceItemResponseDto {
  @Schema(description = "The billed product.", implementation = ProductResponseDto.class)
  private ProductResponseDto product;

  @Schema(description = "The quantity of the billed product.")
  private Integer quantity;

  @Schema(description = "The unit price of the billed product.")
  private BigDecimal unitPrice;
}
