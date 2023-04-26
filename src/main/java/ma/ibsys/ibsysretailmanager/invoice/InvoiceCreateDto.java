package ma.ibsys.ibsysretailmanager.invoice;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "Invoice Request Schema", description = "Request body for creating an Invoice")
public class InvoiceCreateDto {
  @Schema(description = "The ID of the customer who the invoice is issued to")
  @NotNull(message = "customerId cannot be null")
  private Long customerId;
  
  @Schema(description = "The list of items and their quantities to be included in the invoice")
  @NotEmpty(message = "items list cannot be empty")
  private List<@Valid InvoiceItemDto> items;
}
