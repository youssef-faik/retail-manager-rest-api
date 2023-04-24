package ma.ibsys.ibsysretailmanager.invoice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@SecurityRequirement(name = "Bearer Authentication")
@Tag(
    name = "Invoice",
    description =
        "The Invoice API. Contains all the operations that can be performed on an invoice.")
public class InvoiceController {
  private final InvoiceService invoiceService;

  @GetMapping
  @Operation(
      summary = "Get all invoices",
      description = "Get a list that contains the details for all invoices.")
  public ResponseEntity<List<InvoiceDto>> getAllInvoices() {
    return invoiceService.getAllInvoices();
  }

  @GetMapping("/{id}")
  @Operation(
      summary = "Get invoice details",
      description = "Get the details of the invoice with the given id.")
  public ResponseEntity<InvoiceDto> getInvoice(@PathVariable int id) {
    return invoiceService.getInvoiceById(id);
  }

  @PostMapping
  @Operation(
      summary = "Create invoice",
      description = "Create a new invoice with the supplied details.")
  public ResponseEntity<InvoiceDto> createInvoice(@RequestBody InvoiceCreateDto invoiceCreateDto) {
    return invoiceService.createInvoice(invoiceCreateDto);
  }
}
