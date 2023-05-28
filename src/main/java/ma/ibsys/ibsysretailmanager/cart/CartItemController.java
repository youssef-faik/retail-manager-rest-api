package ma.ibsys.ibsysretailmanager.cart;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CartItemController implements CartItemApi {
  private final CartItemService cartItemService;

  @Override
  public ResponseEntity<CartItem> geCartItemById(@PathVariable Long id) {
    CartItem cartItem = cartItemService.getCartItem(id);
    return ResponseEntity.ok(cartItem);
  }

  @Override
  public ResponseEntity<Void> saveCartItem(@Valid @RequestBody CartItemRequestDto cartItemRequest) {
    cartItemService.saveCartItem(cartItemRequest);
    return ResponseEntity.ok().build();
  }
}
