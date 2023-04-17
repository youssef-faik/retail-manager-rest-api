package ma.ibsys.ibsysretailmanager.invoice;

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
public class InvoiceCreateDto {
  @NotNull(message = "customerId cannot be null")
  private Long customerId;

  @NotEmpty(message = "items list cannot be empty")
  private List<@Valid InvoiceItemCreateDto> items;
}
