package ma.ibsys.ibsysretailmanager.cart;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "CartItem")
@Table(name = "cartItem")
public class CartItem {
  @Id @GeneratedValue private Long id;
  private int productId;

  @Pattern(regexp = "^\\d*$", message = "Format de code-barres invalide.")
  private String barcode;
}
