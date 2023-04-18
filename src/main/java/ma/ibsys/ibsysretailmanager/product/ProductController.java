package ma.ibsys.ibsysretailmanager.product;

import java.net.URI;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Product", description = "The Product API. Contains all the operations that can be performed on a product")
public class ProductController {
  private final ProductService productService;
  
  @GetMapping
  @Operation(summary = "Get all products", description = "get a list contains the details for all products.")
  public ResponseEntity<List<ProductResponseDto>> getAllCustomers() {
    List<ProductResponseDto> products = productService.getAllProducts();
    return ResponseEntity.ok(products);
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get product details", description = "Get a the details of a product withe given id.")
  public ResponseEntity<ProductResponseDto> getProduct(@PathVariable("id") int id) {
    ProductResponseDto product = productService.getProductById(id);
    return ResponseEntity.ok(product);
  }

  @PostMapping
  @Operation(summary = "Create product", description = "Create a new product with the given details.")
  public ResponseEntity<ProductResponseDto> createProduct(
      @RequestBody ProductRequestDto productRequestDto) {
    ProductResponseDto createdProduct = productService.createProduct(productRequestDto);
    return ResponseEntity.created(URI.create("/api/products/" + createdProduct.getId()))
        .body(createdProduct);
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update product details", description = "Update the details of a product with given id.")
  public ResponseEntity<ProductResponseDto> updateProduct(
      @PathVariable("id") int id, @RequestBody ProductRequestDto productRequestDto) {
    ProductResponseDto updatedProduct = productService.updateProduct(id, productRequestDto);
    return ResponseEntity.ok(updatedProduct);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete product", description = "Delete a product with given id.")
  public ResponseEntity<Void> deleteProduct(@PathVariable("id") int id) {
    productService.deleteProduct(id);
    return ResponseEntity.noContent().build();
  }
}
