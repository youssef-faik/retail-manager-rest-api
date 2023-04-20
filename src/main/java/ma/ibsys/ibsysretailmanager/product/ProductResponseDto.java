package ma.ibsys.ibsysretailmanager.product;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
  private String name;
  private BigDecimal priceExcludingTax;
  private TaxRate taxRate;
}
