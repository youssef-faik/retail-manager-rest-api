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
@Schema(
        title = "Invoice Create Request Schema",
        description = "Request body for creating an invoice.")
public class InvoiceCreateDto {
  @Schema(
          description = "The ICE number of the customer to whom the invoice is issued.",
          example = "563456789123456")
  @NotNull(message = "customerICE cannot be null.")
  private String customerICE;

  @Schema(description = "The list of items and their quantities to include in the invoice.")
  @NotEmpty(message = "The list of items cannot be empty.")
  private List<@Valid InvoiceItemDto> items;
}
