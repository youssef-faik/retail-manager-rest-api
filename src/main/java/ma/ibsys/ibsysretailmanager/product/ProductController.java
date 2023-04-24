package ma.ibsys.ibsysretailmanager.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@SecurityRequirement(name = "Bearer Authentication")
@Tag(
    name = "Product",
    description = "The Product API. Contains all the operations that can be performed on a product")
public class ProductController {
  private final ProductService productService;

  @GetMapping
  @Operation(
      summary = "Get all products",
      description = "Get a list that contains the details for all products.")
  public ResponseEntity<List<ProductResponseDto>> getAllCustomers() {
    return productService.getAllProducts();
  }

  @GetMapping("/{id}")
  @Operation(
      summary = "Get product details",
      description = "Get the details of the product with the given id.")
  public ResponseEntity<ProductResponseDto> getProduct(@PathVariable("id") int id) {
    return productService.getProductById(id);
  }

  @PostMapping
  @Operation(
      summary = "Create product",
      description = "Create a new product with the given details.")
  public ResponseEntity<ProductResponseDto> createProduct(
      @RequestBody ProductRequestDto productRequestDto) {
    return productService.createProduct(productRequestDto);
  }

  @PutMapping("/{id}")
  @Operation(
      summary = "Update product details",
      description = "Update the details of the product with given id.")
  public ResponseEntity<ProductResponseDto> updateProduct(
      @PathVariable("id") int id, @RequestBody ProductRequestDto productRequestDto) {
    return productService.updateProduct(id, productRequestDto);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete product", description = "Delete a product with given id.")
  public ResponseEntity<Void> deleteProduct(@PathVariable("id") int id) {
    return productService.deleteProduct(id);
  }
}
