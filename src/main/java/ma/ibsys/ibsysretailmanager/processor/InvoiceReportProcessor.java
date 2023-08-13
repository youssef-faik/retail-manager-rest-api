package ma.ibsys.ibsysretailmanager.processor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import ma.ibsys.ibsysretailmanager.invoice.Invoice;
import ma.ibsys.ibsysretailmanager.invoice.InvoiceItem;
import ma.ibsys.ibsysretailmanager.invoice.InvoiceRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import org.springframework.stereotype.Component;
import pl.allegro.finance.tradukisto.MoneyConverters;

@Component
@RequiredArgsConstructor
public class InvoiceReportProcessor {
    private final InvoiceRepository invoiceRepository;

    public ByteArrayOutputStream generateInvoice(int invoiceId) throws JRException {
        Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow();
        List<InvoiceReportInvoiceItem> invoiceReportInvoiceItems = new ArrayList<>();

        BigDecimal montantHT = BigDecimal.ZERO;
        BigDecimal montantTVA = BigDecimal.ZERO;
        BigDecimal montantTTC = BigDecimal.ZERO;

        for (InvoiceItem item  : invoice.getItems()) {
            BigDecimal prixHT = item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity()).setScale(2, RoundingMode.DOWN));
            BigDecimal taxe = prixHT.multiply(BigDecimal.valueOf((item.getProduct().getTaxRate().getValue() / 100.0)).setScale(2, RoundingMode.DOWN));
            BigDecimal montant = prixHT.add(taxe).setScale(2, RoundingMode.DOWN);

            montantHT = montantHT.add(prixHT);
            montantTVA = montantTVA.add(taxe);
            montantTTC = montantTTC.add(montant);


            InvoiceReportInvoiceItem invoiceReportInvoiceItem =
                    InvoiceReportInvoiceItem.builder()
                            .description(item.getProduct().getName())
                            .quantites(item.getQuantity())
                            .prixHT(item.getUnitPrice())
                            .montant(montant)
                            .taxeRate(item.getProduct().getTaxRate().getValue())
                            .build();

            invoiceReportInvoiceItems.add(invoiceReportInvoiceItem);
        }

        JRBeanCollectionDataSource invoiceItemsDataSet = new JRBeanCollectionDataSource(invoiceReportInvoiceItems);

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", invoice.getId());
        parameters.put("issueDate", invoice.getIssueDate());
        parameters.put("name", invoice.getCustomer().getName());
        parameters.put("address", invoice.getCustomer().getAddress());
        parameters.put("ICE", invoice.getCustomer().getICE());
        parameters.put("invoiceItemsDataSet", invoiceItemsDataSet);
        parameters.put("amountInWords", getMoneyIntoWords(montantTTC.toString()));
        parameters.put("montantHT", montantHT);
        parameters.put("montantTVA",montantTVA);
        parameters.put("montantTTC",montantTTC);

        String filePath = "/templates/invoiceReport.jrxml";

        JasperReport report = null;
        try (InputStream inputStream = getClass().getResourceAsStream(filePath)) {
            if (inputStream != null) {
                // Process the input stream
                report = JasperCompileManager.compileReport(inputStream);
            } else {
                System.out.println("Resource not found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        JasperPrint print = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream() ;
        JRPdfExporter exporter = new JRPdfExporter();
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        configuration.setCompressed(true);
        exporter.setConfiguration (configuration) ;
        exporter.setExporterInput(new SimpleExporterInput(print));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
        exporter.exportReport();

        return byteArrayOutputStream;
    }

    public String getMoneyIntoWords(String input) {
        MoneyConverters converter = MoneyConverters.FRENCH_BANKING_MONEY_VALUE;

        String[] amounts = input.split("\\.");
        BigDecimal dirhams = new BigDecimal(amounts[0]);
        BigDecimal centimes = new BigDecimal(amounts[1]);
        String output = "";

        if (dirhams.compareTo(BigDecimal.ONE) == 1){
            output = converter.asWords(dirhams).split("€")[0] + " DIRHAMS";
        } else {
            output = converter.asWords(dirhams).split("€")[0] + " DIRHAM";
        }

        if (centimes.compareTo(BigDecimal.ZERO) == 1){
            output = output.concat(", ET " + converter.asWords(centimes).split("€")[0] + " CENTIMES");
        }

        return output.toUpperCase();
    }

}
