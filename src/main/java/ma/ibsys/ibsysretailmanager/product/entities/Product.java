package ma.ibsys.ibsysretailmanager.product.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ibsys.ibsysretailmanager.product.enums.TaxRate;

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

  @NotBlank(message = "barCode is mandatory")
  @Column(name = "bar_code", nullable = false)
  private String barCode;

  @NotBlank(message = "priceExcludingTax is mandatory")
  @PositiveOrZero(message = "priceExcludingTax must be a positive number or 0")
  @Column(name = "price_excluding_tax", nullable = false)
  private BigDecimal priceExcludingTax;

  @Enumerated(EnumType.STRING)
  @NotBlank(message = "taxRate is mandatory")
  @Column(name = "tax_rate", nullable = false)
  private TaxRate taxRate;
}
