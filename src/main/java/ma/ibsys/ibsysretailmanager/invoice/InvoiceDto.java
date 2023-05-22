package ma.ibsys.ibsysretailmanager.invoice;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ibsys.ibsysretailmanager.customer.CustomerResponseDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "Invoice Response Schema", description = "Response body for an invoice response")
public class InvoiceDto {
  @Schema(description = "The ID of the invoice")
  private int id;

  @Schema(description = "The date on which the invoice was issued")
  private LocalDate issueDate;

  @Schema(description = "The customer who the invoice is issued to")
  private CustomerResponseDto customer;

  @Schema(description = "The list of items and their quantities included in the invoice")
  private List<InvoiceItemResponseDto> items;
}
