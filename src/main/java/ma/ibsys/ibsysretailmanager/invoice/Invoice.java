package ma.ibsys.ibsysretailmanager.invoice;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ibsys.ibsysretailmanager.customer.Customer;

@Entity(name = "Invoice")
@Table(name = "invoice")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
  @Id
  @Column(name = "id", updatable = false)
  private int id;

  @NotNull(message = "Issue date is mandatory")
  @Column(name = "issueDate", nullable = false)
  private LocalDate issueDate = LocalDate.now();

  @NotNull(message = "Customer is mandatory")
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(
          name = "customer_ice",
          referencedColumnName = "ICE",
          nullable = false,
          foreignKey = @ForeignKey(name = "invoice_customer_ice_fk"))
  private Customer customer;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "invoice", fetch = FetchType.EAGER)
  private List<InvoiceItem> items = new ArrayList<>();

  public void addItem(InvoiceItem invoiceItem) {
    if (!this.items.contains(invoiceItem)) {
      this.items.add(invoiceItem);
      invoiceItem.setInvoice(this);
    }
  }
}
