package ma.ibsys.ibsysretailmanager.product;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController implements ProductApi {
  private final ProductService productService;

  @Override
  public ResponseEntity<List<ProductResponseDto>> getAllCustomers() {
    return productService.getAllProducts();
  }

  @Override
  public ResponseEntity<ProductResponseDto> getProduct(@PathVariable("id") int id) {
    return productService.getProductById(id);
  }

  @Override
  public ResponseEntity<Void> createProduct(
      @RequestBody ProductRequestDto productRequestDto) {
    return productService.createProduct(productRequestDto);
  }

  @Override
  public ResponseEntity<ProductResponseDto> updateProduct(
      @PathVariable("id") int id, @RequestBody ProductRequestDto productRequestDto) {
    return productService.updateProduct(id, productRequestDto);
  }

  @Override
  public ResponseEntity<Void> deleteProduct(@PathVariable("id") int id) {
    return productService.deleteProduct(id);
  }
}
