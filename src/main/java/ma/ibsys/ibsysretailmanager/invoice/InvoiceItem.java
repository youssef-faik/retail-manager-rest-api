package ma.ibsys.ibsysretailmanager.invoice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ibsys.ibsysretailmanager.product.entities.Product;

import java.math.BigDecimal;

@Entity(name = "InvoiceItem")
@Table(name = "invoice-item")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InvoiceItem {
  @EmbeddedId
  private InvoiceItemId id;
  
  @NotNull(message = "invoice is mandatory")
  @ManyToOne
  @MapsId(value = "invoiceId")
  @JoinColumn(
          name = "invoice_id",
          referencedColumnName = "id",
          foreignKey = @ForeignKey(name = "invoice_item_invoice_id_fk")
  )
  private Invoice invoice;
  
  @NotNull(message = "product is mandatory")
  @ManyToOne
  @MapsId(value = "productId")
  @JoinColumn(
          name = "product_id",
          referencedColumnName = "id",
          foreignKey = @ForeignKey(name = "invoice_item_product_id_fk")
  )
  private Product product;
  
  @Positive(message = "quantity must be greater than zero")
  @Column(
          name = "quantity",
          nullable = false,
          updatable = false
  )
  private int quantity;
  
  @NotNull(message = "unitPrice is mandatory")
  @Column(name = "unit_price", nullable = false)
  private BigDecimal unitPrice;
  
  
}
