package ma.ibsys.ibsysretailmanager.invoice;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ibsys.ibsysretailmanager.product.Product;

@Entity(name = "InvoiceItem")
@Table(name = "invoice-item")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Schema(
    title = "Entité d'article de facture.",
    description = "Représente un article dans une facture.")
public class InvoiceItem {
  @EmbeddedId private InvoiceItemId id;

  @NotNull(message = "La facture est obligatoire.")
  @ManyToOne
  @MapsId(value = "invoiceId")
  @JoinColumn(
      name = "invoice_id",
      referencedColumnName = "id",
      foreignKey = @ForeignKey(name = "invoice_item_invoice_id_fk"))
  private Invoice invoice;

  @NotNull(message = "Le produit est obligatoire.")
  @ManyToOne(cascade = CascadeType.ALL)
  @MapsId(value = "productId")
  @JoinColumn(
      name = "product_id",
      referencedColumnName = "id",
      foreignKey = @ForeignKey(name = "invoice_item_product_id_fk"))
  private Product product;

  @Positive(message = "La quantité doit être supérieure à zéro.")
  @Column(name = "quantity", nullable = false, updatable = false)
  private int quantity;

  @NotNull(message = "Le prix unitaire est obligatoire.")
  @Column(name = "unit_price", nullable = false)
  private BigDecimal unitPrice;
}
