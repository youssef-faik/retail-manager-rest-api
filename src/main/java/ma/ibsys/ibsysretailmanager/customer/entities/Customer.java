package ma.ibsys.ibsysretailmanager.customer.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ibsys.ibsysretailmanager.invoice.entities.Invoice;

@Entity(name = "Customer")
@Table(
    name = "customer",
    uniqueConstraints = {
      @UniqueConstraint(name = "customer_name_unique", columnNames = "name"),
      @UniqueConstraint(name = "customer_email_unique", columnNames = "email")
    })
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false)
  private int id;

  @Size(min = 2, max = 100, message = "name must be between {min} and {max} characters long")
  @NotBlank(message = "name is mandatory")
  @Column(name = "name", nullable = false)
  private String name;

  @Email(message = "email should be valid")
  @NotBlank(message = "email is mandatory")
  @Column(name = "email", nullable = false)
  private String email;
  
  @Size(min = 10, max = 10, message = "phone_number must be between {min} and {max} characters long")
  @NotBlank(message = "phone_number is mandatory")
  @Column(name = "phone_number", nullable = false, length = 10)
  private String phoneNumber;

  @Size(min = 10, max = 500, message = "address must be between {min} and {max} characters long")
  @NotBlank(message = "address is mandatory")
  @Column(name = "address", nullable = false)
  private String address;
  
  @OneToMany(mappedBy = "customer")
  private List<Invoice> invoices = new ArrayList<>();
  
  public void addInvoice(Invoice invoice){
    if (!this.invoices.contains(invoice)) {
      this.invoices.add(invoice);
      invoice.setCustomer(this);
    }
  }
}
