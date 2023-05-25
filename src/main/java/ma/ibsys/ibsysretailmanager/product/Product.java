package ma.ibsys.ibsysretailmanager.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Product")
@Table(
    name = "product",
    uniqueConstraints = {
      @UniqueConstraint(name = "product_bar_code_unique", columnNames = "bar_code")
    })
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false)
  private int id;

  @NotBlank(message = "BarCode est obligatoire")
  @Column(name = "bar_code", nullable = false)
  private String barCode;

  @Size(min = 2, max = 1000, message = "Le nom doit comporter entre {min} et {max} caractères")
  @NotBlank(message = "Le nom est obligatoire")
  @Column(name = "name", nullable = false)
  private String name;

  @NotNull(message = "Le prix de vente hors taxe est obligatoire")
  @Positive(message = "Le prix de vente hors taxe doit être un nombre positif et superior a zero.")
  @Column(name = "selling_price_excluding_tax", nullable = false)
  private BigDecimal sellingPriceExcludingTax;

  @NotNull(message = "Le prix d'achat est obligatoire")
  @Positive(message = "Le prix d'achat doit être un nombre positif et superior a zero.")
  @Column(name = "purchase-price", nullable = false)
  private BigDecimal purchasePrice;

  @Enumerated(EnumType.STRING)
  @NotNull(message = "Le taux de TVA est obligatoire")
  @Column(name = "tax_rate", nullable = false)
  private TaxRate taxRate;
}
