package ma.ibsys.ibsysretailmanager.invoice;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class InvoiceItemId implements Serializable {
  @Column(name = "invoice_id", nullable = false)
  private int invoiceId;
  
  @Column(name = "product_id", nullable = false)
  private int productId;
}
