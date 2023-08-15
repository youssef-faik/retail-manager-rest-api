package ma.ibsys.ibsysretailmanager.customer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ibsys.ibsysretailmanager.invoice.Invoice;
import org.hibernate.annotations.CreationTimestamp;

@Entity(name = "Customer")
@Table(
        name = "customer",
        uniqueConstraints = {
                @UniqueConstraint(name = "customer_name_unique", columnNames = "name"),
                @UniqueConstraint(name = "customer_ice_unique", columnNames = "ice")
        })
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
  @Id
  @NotNull
  @Pattern(
          regexp = "^\\d{15}$",
          message =
                  "The ICE number must be exactly 15 characters long and should only contain digits.")
  @Column(name = "ice", nullable = false, length = 15)
  private String ICE;

  @Size(
          min = 2,
          max = 100,
          message = "The name must have a length between {min} and {max} characters.")
  @NotBlank(message = "The name field is required.")
  @Column(name = "name", nullable = false)
  private String name;

  @Email(message = "The email address must be valid.")
  @Column(name = "email")
  private String email;

  @NotNull(message = "The phone number is required.")
  @Pattern(
          regexp = "^0\\d{9}$",
          message = "The phone number must be exactly 10 digits and contain only digits.")
  @Column(name = "phone", nullable = false, length = 10)
  private String phone;

  @Size(
          min = 10,
          max = 300,
          message = "The address must have a length between {min} and {max} characters.")
  @NotBlank(message = "The address is required.")
  @Column(name = "address", nullable = false)
  private String address;

  @Column(name = "created_at", nullable = false, updatable = false)
  @CreationTimestamp
  private LocalDate createdAt;

  @OneToMany(mappedBy = "customer")
  private List<Invoice> invoices = new ArrayList<>();

  public void addInvoice(Invoice invoice) {
    if (!this.invoices.contains(invoice)) {
      this.invoices.add(invoice);
      invoice.setCustomer(this);
    }
  }
}
