package ma.ibsys.ibsysretailmanager.product;

import jakarta.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import ma.ibsys.ibsysretailmanager.category.Category;
import ma.ibsys.ibsysretailmanager.category.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
  private final ProductRepository productRepository;
  private final ModelMapper modelMapper;
  private final CategoryRepository categoryRepository;

  public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
    List<ProductResponseDto> products =
            productRepository.findAll().stream()
                    .map(product -> modelMapper.map(product, ProductResponseDto.class))
                    .collect(Collectors.toList());

    return ResponseEntity.ok(products);
  }

  public ResponseEntity<ProductResponseDto> getProductById(int id) {
    Product product =
            productRepository
                    .findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Product not found with ID " + id));

    return ResponseEntity.ok(modelMapper.map(product, ProductResponseDto.class));
  }

  public ResponseEntity<Void> createProduct(ProductRequestDto productRequestDto) {
    Product product = modelMapper.map(productRequestDto, Product.class);
    Product savedProduct = productRepository.save(product);
    return ResponseEntity.created(URI.create("/api/v1/products/" + savedProduct.getId())).build();
  }

  public ResponseEntity<ProductResponseDto> updateProduct(
          int id, ProductRequestDto productRequestDto) {
    Product product =
            productRepository
                    .findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Product not found with ID " + id));

    Category category =
            categoryRepository
                    .findById(productRequestDto.getCategory())
                    .orElseThrow(
                            () ->
                                    new EntityNotFoundException(
                                            "Category not found with ID " + productRequestDto.getCategory()));

    product.setBarCode(productRequestDto.getBarCode());
    product.setTaxRate(productRequestDto.getTaxRate());
    product.setCategory(category);
    product.setName(productRequestDto.getName());
    product.setSellingPriceExcludingTax(productRequestDto.getSellingPriceExcludingTax());
    product.setPurchasePrice(productRequestDto.getPurchasePrice());
    Product updatedProduct = productRepository.save(product);

    return ResponseEntity.ok(modelMapper.map(updatedProduct, ProductResponseDto.class));
  }

  public ResponseEntity<Void> deleteProduct(int id) {
    Product product =
            productRepository
                    .findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Product not found with ID " + id));
    productRepository.delete(product);

    return ResponseEntity.noContent().build();
  }
}
