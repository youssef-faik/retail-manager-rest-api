package ma.ibsys.ibsysretailmanager.customer;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
          "Le numéro ICE doit être composé d'exactement 15 caractères et ne doit contenir que des chiffres.")
  @Column(name = "ice", nullable = false, length = 15)
  private String ICE;

  @Size(
      min = 2,
      max = 100,
      message = "Le nom doit avoir une longueur comprise entre {min} et {max} caractères.")
  @NotBlank(message = "Le champ du nom est obligatoire.")
  @Column(name = "name", nullable = false)
  private String name;

  @Email(message = "L'adresse email doit être valide.")
  @Column(name = "email")
  private String email;

  @NotNull(message = "Le numéro de téléphone est obligatoire. ")
  @Pattern(
      regexp = "^0\\d{9}$",
      message =
          "Le numéro de téléphone doit comporter exactement 10 chiffres et ne contenir que des chiffres.")
  @Column(name = "phone", nullable = false, length = 10)
  private String phone;

  @Size(
      min = 10,
      max = 300,
      message = "L'adresse doit avoir une longueur comprise entre {min} et {max} caractères.")
  @NotBlank(message = "L'adresse est obligatoire.")
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
