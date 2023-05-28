package ma.ibsys.ibsysretailmanager.cart;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import ma.ibsys.ibsysretailmanager.product.Product;
import ma.ibsys.ibsysretailmanager.product.ProductRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemService {
  private final SimpMessagingTemplate simpMessagingTemplate;
  private final CartItemRepository cartItemRepository;
  private final ProductRepository productRepository;

  public CartItem getCartItem(Long id) {
    CartItem cartItem =
        cartItemRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("CartItem introuvable avec l'ID " + id));
    return cartItem;
  }

  public void saveCartItem(CartItemRequestDto cartItemRequest) {
    if (cartItemRequest.getBarcode().equalsIgnoreCase("101")) {
      CartItem cartItem =
          CartItem.builder().productId(0).barcode(cartItemRequest.getBarcode()).build();
      CartItem savedCartItem = cartItemRepository.save(cartItem);

      this.send(
          CartItemUpdateMessage.builder().addedCartItem(savedCartItem).build(),
          cartItemRequest.getCartId());
      return;
    }

    Product product =
        productRepository
            .findByBarCode(cartItemRequest.getBarcode())
            .orElseThrow(
                () ->
                    new EntityNotFoundException(
                        "Produit introuvable avec le barcode " + cartItemRequest.getBarcode()));

    CartItem cartItem =
        CartItem.builder().productId(product.getId()).barcode(cartItemRequest.getBarcode()).build();
    CartItem savedCartItem = cartItemRepository.save(cartItem);

    this.send(
        CartItemUpdateMessage.builder().addedCartItem(savedCartItem).build(),
        cartItemRequest.getCartId());
  }

  private void send(CartItemUpdateMessage message, String cartId) {
    this.simpMessagingTemplate.convertAndSend("/cart/" + cartId, message);
  }
}
