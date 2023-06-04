package ma.ibsys.ibsysretailmanager.invoice;

import jakarta.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import ma.ibsys.ibsysretailmanager.configuration.AppConfiguration;
import ma.ibsys.ibsysretailmanager.configuration.ConfigKey;
import ma.ibsys.ibsysretailmanager.configuration.ConfigOptionDto;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            .orElseThrow(
                () -> new EntityNotFoundException("Facture non trouvée avec l'identifiant " + id));
    return ResponseEntity.ok(modelMapper.map(invoice, InvoiceDto.class));
  }

  @Transactional
  public ResponseEntity<Void> createInvoice(InvoiceCreateDto invoiceCreateDto) {
    Invoice mappedInvoice = modelMapper.map(invoiceCreateDto, Invoice.class);

    AppConfiguration configuration = AppConfiguration.getInstance();
    int lastInvoiceNumber =
        Integer.valueOf(
            configuration.getConfigurationValue(ConfigKey.LAST_INVOICE_NUMBER).getValue());

    lastInvoiceNumber++;
    mappedInvoice.setId(lastInvoiceNumber);

    Invoice savedInvoice = invoiceRepository.save(mappedInvoice);
    int id = savedInvoice.getId();

    configuration.setConfigurationValues(
        List.of(
            ConfigOptionDto.builder()
                .key(ConfigKey.LAST_INVOICE_NUMBER)
                .value(String.valueOf(lastInvoiceNumber))
                .build()));

    return ResponseEntity.created(URI.create("/api/v1/invoices/" + id)).build();
  }
}
