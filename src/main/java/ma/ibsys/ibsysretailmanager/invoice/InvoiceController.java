package ma.ibsys.ibsysretailmanager.invoice;

import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/invoices")
@RequiredArgsConstructor
public class InvoiceController {
  private final InvoiceService invoiceService;

  @GetMapping
  public ResponseEntity<List<InvoiceDto>> getAllInvoices() {
    List<InvoiceDto> invoices = invoiceService.getAllInvoices();
    return ResponseEntity.ok(invoices);
  }

  @GetMapping("/{id}")
  public ResponseEntity<InvoiceDto> getInvoice(@PathVariable int id) {
    InvoiceDto invoice = invoiceService.getInvoiceById(id);
    return ResponseEntity.ok(invoice);
  }

  @PostMapping
  public ResponseEntity<InvoiceDto> createInvoice(@RequestBody InvoiceCreateDto invoiceCreateDto) {
    InvoiceDto createdInvoice = invoiceService.createInvoice(invoiceCreateDto);
    return ResponseEntity.created(URI.create("/invoices/" + createdInvoice.getId()))
        .body(createdInvoice);
  }
}
