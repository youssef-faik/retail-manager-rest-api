package ma.ibsys.ibsysretailmanager.product;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
  private final ProductRepository productRepository;
  private final ModelMapper modelMapper;

  public List<ProductResponseDto> getAllProducts() {
    List<Product> products = productRepository.findAll();
    return products.stream()
        .map(product -> modelMapper.map(product, ProductResponseDto.class))
        .collect(Collectors.toList());
  }

  public ProductResponseDto getProductById(int id) {
    Product product =
        productRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + id));
    return modelMapper.map(product, ProductResponseDto.class);
  }

  public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
    Product product = modelMapper.map(productRequestDto, Product.class);
    Product savedProduct = productRepository.save(product);
    return modelMapper.map(savedProduct, ProductResponseDto.class);
  }

  public ProductResponseDto updateProduct(int id, ProductRequestDto productRequestDto) {
    Product product =
        productRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + id));
    product.setBarCode(productRequestDto.getBarCode());
    product.setTaxRate(productRequestDto.getTaxRate());
    product.setName(productRequestDto.getName());
    product.setPriceExcludingTax(productRequestDto.getPriceExcludingTax());
    Product updatedProduct = productRepository.save(product);
    return modelMapper.map(updatedProduct, ProductResponseDto.class);
  }

  public void deleteProduct(int id) {
    Product product =
        productRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + id));
    productRepository.delete(product);
  }
}
