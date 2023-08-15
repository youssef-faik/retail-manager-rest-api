package ma.ibsys.ibsysretailmanager.invoice;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ibsys.ibsysretailmanager.product.Product;

@Entity(name = "InvoiceItem")
@Table(name = "invoice_item")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Schema(
        title = "Invoice Item Entity",
        description = "Represents an item in an invoice.")
public class InvoiceItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotNull(message = "The invoice is required.")
  @ManyToOne
  @JoinColumn(
          name = "invoice_id",
          referencedColumnName = "id",
          foreignKey = @ForeignKey(name = "invoice_item_invoice_id_fk"))
  private Invoice invoice;

  @NotNull(message = "The product is required.")
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(
          name = "product_id",
          referencedColumnName = "id",
          foreignKey = @ForeignKey(name = "invoice_item_product_id_fk"))
  private Product product;

  @Positive(message = "The quantity must be greater than zero.")
  @Column(name = "quantity", nullable = false, updatable = false)
  private int quantity;

  @NotNull(message = "The unit price is required.")
  @Column(name = "unit_price", nullable = false)
  private BigDecimal unitPrice;
}
