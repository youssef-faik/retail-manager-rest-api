package ma.ibsys.ibsysretailmanager.invoice;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDto {
  private int id;
  private int invoiceNumber;
  private LocalDate issueDate;
  private Long customerId;
  private List<InvoiceItemDto> items;
}
