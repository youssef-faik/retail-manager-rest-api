package ma.ibsys.ibsysretailmanager.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class CartItemUpdateMessage {
  private final CartItem addedCartItem;
}
