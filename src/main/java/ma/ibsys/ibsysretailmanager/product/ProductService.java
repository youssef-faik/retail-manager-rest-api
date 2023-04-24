package ma.ibsys.ibsysretailmanager.product;

import jakarta.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
  private final ProductRepository productRepository;
  private final ModelMapper modelMapper;

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
            .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + id));

    return ResponseEntity.ok(modelMapper.map(product, ProductResponseDto.class));
  }

  public ResponseEntity<ProductResponseDto> createProduct(ProductRequestDto productRequestDto) {
    Product product = modelMapper.map(productRequestDto, Product.class);
    Product savedProduct = productRepository.save(product);
    return ResponseEntity.created(URI.create("/api/products/" + savedProduct.getId())).build();
  }

  public ResponseEntity<ProductResponseDto> updateProduct(
      int id, ProductRequestDto productRequestDto) {
    Product product =
        productRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + id));
    product.setBarCode(productRequestDto.getBarCode());
    product.setTaxRate(productRequestDto.getTaxRate());
    product.setName(productRequestDto.getName());
    product.setPriceExcludingTax(productRequestDto.getPriceExcludingTax());
    Product updatedProduct = productRepository.save(product);

    return ResponseEntity.ok(modelMapper.map(updatedProduct, ProductResponseDto.class));
  }

  public ResponseEntity<Void> deleteProduct(int id) {
    Product product =
        productRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + id));
    productRepository.delete(product);

    return ResponseEntity.noContent().build();
  }
}
