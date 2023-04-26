package ma.ibsys.ibsysretailmanager.invoice;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "Invoice Item Schema", description = "Represents an item included in an invoice")
public class InvoiceItemDto {
  @Schema(description = "The ID of the product being invoiced")
  private int productId;
  @Schema(description = "The quantity of the product being invoiced")
  private int quantity;
  @Schema(description = "The unit price of the product being invoiced")
  private BigDecimal unitPrice;
}
