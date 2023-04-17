package ma.ibsys.ibsysretailmanager.invoice;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvoiceService {

  private final InvoiceRepository invoiceRepository;
  private final ModelMapper modelMapper;

  public List<InvoiceDto> getAllInvoices() {
    List<Invoice> invoices = invoiceRepository.findAll();
    return invoices.stream()
        .map(invoice -> modelMapper.map(invoice, InvoiceDto.class))
        .collect(Collectors.toList());
  }

  public InvoiceDto getInvoiceById(int id) {
    Invoice invoice =
        invoiceRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Invoice not found with id: " + id));
    return modelMapper.map(invoice, InvoiceDto.class);
  }

  public InvoiceDto createInvoice(InvoiceCreateDto invoiceCreateDto) {
    Invoice invoice = modelMapper.map(invoiceCreateDto, Invoice.class);
    for (InvoiceItem item : invoice.getItems()) {
      item.setInvoice(invoice);
    }

    Invoice savedInvoice = invoiceRepository.save(invoice);
    return modelMapper.map(savedInvoice, InvoiceDto.class);
  }
}
