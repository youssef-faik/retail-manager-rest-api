package ma.ibsys.ibsysretailmanager.product;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ibsys.ibsysretailmanager.category.Category;

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

  @NotBlank(message = "BarCode is mandatory")
  @Column(name = "bar_code", nullable = false)
  private String barCode;

  @Size(min = 2, max = 1000, message = "Name must be between {min} and {max} characters")
  @NotBlank(message = "Name is mandatory")
  @Column(name = "name", nullable = false)
  private String name;

  @NotNull(message = "Selling price excluding tax is mandatory")
  @Positive(message = "Selling price excluding tax must be a positive number and greater than zero.")
  @Column(name = "selling_price_excluding_tax", nullable = false)
  private BigDecimal sellingPriceExcludingTax;

  @NotNull(message = "Purchase price is mandatory")
  @Positive(message = "Purchase price must be a positive number and greater than zero.")
  @Column(name = "purchase_price", nullable = false)
  private BigDecimal purchasePrice;

  @Enumerated(EnumType.STRING)
  @NotNull(message = "Tax rate is mandatory")
  @Column(name = "tax_rate", nullable = false)
  private TaxRate taxRate;

  @NotNull(message = "Category is mandatory")
  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "category_id", nullable = false)
  private Category category;
}
