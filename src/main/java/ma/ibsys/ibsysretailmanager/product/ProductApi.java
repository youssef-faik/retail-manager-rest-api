package ma.ibsys.ibsysretailmanager.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/products")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(
    name = "Product",
    description = "The Product API. Contains all the operations that can be performed on a product")
public interface ProductApi {
  @GetMapping
  @Operation(
      summary = "Get all products",
      description = "Get a list that contains the details for all products.")
  ResponseEntity<List<ProductResponseDto>> getAllCustomers();

  @GetMapping("/{id}")
  @Operation(
      summary = "Get product details",
      description = "Get the details of the product with the given id.")
  ResponseEntity<ProductResponseDto> getProduct(@PathVariable("id") int id);

  @PostMapping
  @Operation(
      summary = "Create product",
      description = "Create a new product with the given details.")
  ResponseEntity<ProductResponseDto> createProduct(
      @RequestBody ProductRequestDto productRequestDto);

  @PutMapping("/{id}")
  @Operation(
      summary = "Update product details",
      description = "Update the details of the product with given id.")
  ResponseEntity<ProductResponseDto> updateProduct(
      @PathVariable("id") int id, @RequestBody ProductRequestDto productRequestDto);

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete product", description = "Delete a product with given id.")
  ResponseEntity<Void> deleteProduct(@PathVariable("id") int id);
}
