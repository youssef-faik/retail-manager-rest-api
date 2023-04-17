package ma.ibsys.ibsysretailmanager.invoice;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
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
@Table(
    name = "invoice",
    uniqueConstraints = {
      @UniqueConstraint(name = "invoice_invoice_number_unique", columnNames = "invoice_number")
    })
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false)
  private int id;

  @Digits(integer = 10, fraction = 0)
  @NotNull(message = "invoiceNumber is mandatory")
  @Column(name = "invoice_number", nullable = false)
  private int invoiceNumber;

  @NotNull(message = "issueDate is mandatory")
  @Column(name = "issueDate", nullable = false)
  private LocalDate issueDate = LocalDate.now();

  
  @NotNull(message = "customer is mandatory")
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(
      name = "customer_id",
      referencedColumnName = "id",
      nullable = false,
      foreignKey = @ForeignKey(name = "invoice_customer_id_fk"))
  private Customer customer;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "invoice")
  private List<InvoiceItem> items = new ArrayList<>();
  
  public void addItem(InvoiceItem invoiceItem){
    if (!this.items.contains(invoiceItem)) {
      this.items.add(invoiceItem);
    }
  }
}
