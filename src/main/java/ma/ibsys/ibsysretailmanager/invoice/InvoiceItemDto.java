package ma.ibsys.ibsysretailmanager.invoice;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(
        title = "Invoice Item Schema",
        description = "Represents an item included in an invoice.")
public class InvoiceItemDto {
  @Schema(description = "The ID of the billed product.")
  @NotNull(message = "The product ID cannot be null.")
  private Integer productId;

  @Schema(description = "The quantity of the billed product.")
  @NotNull(message = "The quantity cannot be null.")
  @Min(value = 1, message = "The quantity must be at least 1.")
  private Integer quantity;

  @Schema(description = "The unit price of the billed product.")
  @NotNull(message = "The unit price cannot be null.")
  @DecimalMin(value = "1", message = "The unit price must be greater than or equal to {value}.")
  private BigDecimal unitPrice;
}
