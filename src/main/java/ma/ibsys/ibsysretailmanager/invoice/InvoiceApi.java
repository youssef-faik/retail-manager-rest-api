package ma.ibsys.ibsysretailmanager.invoice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/invoices")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(
    name = "Invoice",
    description =
        "The Invoice API. Contains all the operations that can be performed on an invoice.")
public interface InvoiceApi {
  @GetMapping
  @Operation(
      summary = "Get all invoices",
      description = "Get a list that contains the details for all invoices.")
  ResponseEntity<List<InvoiceDto>> getAllInvoices();

  @GetMapping("/{id}")
  @Operation(
      summary = "Get invoice details",
      description = "Get the details of the invoice with the given id.")
  ResponseEntity<InvoiceDto> getInvoice(@PathVariable int id);

  @PostMapping
  @Operation(
      summary = "Create invoice",
      description = "Create a new invoice with the supplied details.")
  ResponseEntity<InvoiceDto> createInvoice(@RequestBody InvoiceCreateDto invoiceCreateDto);
}
