package ma.ibsys.ibsysretailmanager.invoice;

import jakarta.persistence.*;
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

  @NotNull(message = "La date d'émission est obligatoire")
  @Column(name = "issueDate", nullable = false)
  private LocalDate issueDate = LocalDate.now();

  @NotNull(message = "Le client est obligatoire")
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(
      name = "customer_ice",
      referencedColumnName = "ICE",
      nullable = false,
      foreignKey = @ForeignKey(name = "invoice_customer_ice_fk"))
  private Customer customer;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "invoice")
  private List<InvoiceItem> items = new ArrayList<>();

  public void addItem(InvoiceItem invoiceItem) {
    if (!this.items.contains(invoiceItem)) {
      this.items.add(invoiceItem);
      invoiceItem.setInvoice(this);
    }
  }
}
