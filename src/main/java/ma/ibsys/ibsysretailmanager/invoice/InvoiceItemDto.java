package ma.ibsys.ibsysretailmanager.invoice;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceItemDto {
  private int productId;
  private int quantity;
  private BigDecimal unitPrice;
}
