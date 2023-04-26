package ma.ibsys.ibsysretailmanager.invoice;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InvoiceController implements InvoiceApi {
  private final InvoiceService invoiceService;

  @Override
  public ResponseEntity<List<InvoiceDto>> getAllInvoices() {
    return invoiceService.getAllInvoices();
  }

  @Override
  public ResponseEntity<InvoiceDto> getInvoice(@PathVariable int id) {
    return invoiceService.getInvoiceById(id);
  }

  @Override
  public ResponseEntity<Void> createInvoice(@RequestBody InvoiceCreateDto invoiceCreateDto) {
    return invoiceService.createInvoice(invoiceCreateDto);
  }
}
