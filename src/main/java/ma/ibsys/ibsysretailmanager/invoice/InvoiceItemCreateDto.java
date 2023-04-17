package ma.ibsys.ibsysretailmanager.invoice;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceItemCreateDto {
  @NotNull(message = "Product ID cannot be null")
  private Integer productId;

  @NotNull(message = "Quantity cannot be null")
  @Min(value = 1, message = "Quantity must be at least 1")
  private Integer quantity;

  @NotNull(message = "Unit price cannot be null")
  @DecimalMin(value = "1", message = "Unit price must be greater than or equal to {value}")
  private BigDecimal unitPrice;
}
