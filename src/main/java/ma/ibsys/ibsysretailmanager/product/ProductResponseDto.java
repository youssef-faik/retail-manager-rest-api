package ma.ibsys.ibsysretailmanager.product;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {
  private int id;
  private String barCode;
  private BigDecimal priceExcludingTax;
  private TaxRate taxRate;
}
