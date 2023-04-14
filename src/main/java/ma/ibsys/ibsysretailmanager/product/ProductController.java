package ma.ibsys.ibsysretailmanager.product;

import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
  private final ProductService productService;

  @GetMapping
  public ResponseEntity<List<ProductResponseDto>> getAllCustomers() {
    List<ProductResponseDto> products = productService.getAllProducts();
    return ResponseEntity.ok(products);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductResponseDto> getProduct(@PathVariable("id") int id) {
    ProductResponseDto product = productService.getProductById(id);
    return ResponseEntity.ok(product);
  }

  @PostMapping
  public ResponseEntity<ProductResponseDto> createProduct(
      @RequestBody ProductRequestDto productRequestDto) {
    ProductResponseDto createdProduct = productService.createProduct(productRequestDto);
    return ResponseEntity.created(URI.create("/api/products/" + createdProduct.getId()))
        .body(createdProduct);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductResponseDto> updateProduct(
      @PathVariable("id") int id, @RequestBody ProductRequestDto productRequestDto) {
    ProductResponseDto updatedProduct = productService.updateProduct(id, productRequestDto);
    return ResponseEntity.ok(updatedProduct);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable("id") int id) {
    productService.deleteProduct(id);
    return ResponseEntity.noContent().build();
  }
}
