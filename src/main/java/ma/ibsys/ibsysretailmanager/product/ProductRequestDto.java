package ma.ibsys.ibsysretailmanager.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {
  @NotBlank(message = "barCode is mandatory")
  private String barCode;

  @NotBlank(message = "priceExcludingTax is mandatory")
  @PositiveOrZero(message = "priceExcludingTax must be a positive number or 0")
  private BigDecimal priceExcludingTax;

  @NotBlank(message = "taxRate is mandatory")
  private TaxRate taxRate;
}
