package ma.ibsys.ibsysretailmanager.invoice;

import jakarta.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvoiceService {

  private final InvoiceRepository invoiceRepository;
  private final ModelMapper modelMapper;

  public ResponseEntity<List<InvoiceDto>> getAllInvoices() {
    List<InvoiceDto> invoices =
        invoiceRepository.findAll().stream()
            .map(invoice -> modelMapper.map(invoice, InvoiceDto.class))
            .collect(Collectors.toList());

    return ResponseEntity.ok(invoices);
  }

  public ResponseEntity<InvoiceDto> getInvoiceById(int id) {
    Invoice invoice =
        invoiceRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Invoice not found with id: " + id));
    return ResponseEntity.ok(modelMapper.map(invoice, InvoiceDto.class));
  }

  public ResponseEntity<Void> createInvoice(InvoiceCreateDto invoiceCreateDto) {
    Invoice invoice = modelMapper.map(invoiceCreateDto, Invoice.class);
    for (InvoiceItem item : invoice.getItems()) {
      item.setInvoice(invoice);
    }

    Invoice savedInvoice = invoiceRepository.save(invoice);

    return ResponseEntity.created(URI.create("/api/v1/invoices/" + savedInvoice.getId())).build();
  }
}
