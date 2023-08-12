package ma.ibsys.ibsysretailmanager.processor;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceReportInvoiceItem {
    private String description;
    private int quantites;
    private BigDecimal prixHT;
    private BigDecimal montant;
    private int taxeRate;
}
