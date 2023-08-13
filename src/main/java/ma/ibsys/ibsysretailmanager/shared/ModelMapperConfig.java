package ma.ibsys.ibsysretailmanager.shared;

import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import ma.ibsys.ibsysretailmanager.category.Category;
import ma.ibsys.ibsysretailmanager.category.CategoryCreateDto;
import ma.ibsys.ibsysretailmanager.category.CategoryRepository;
import ma.ibsys.ibsysretailmanager.customer.Customer;
import ma.ibsys.ibsysretailmanager.customer.CustomerCreateDto;
import ma.ibsys.ibsysretailmanager.customer.CustomerRepository;
import ma.ibsys.ibsysretailmanager.customer.CustomerResponseDto;
import ma.ibsys.ibsysretailmanager.customer.CustomerUpdateDto;
import ma.ibsys.ibsysretailmanager.invoice.Invoice;
import ma.ibsys.ibsysretailmanager.invoice.InvoiceCreateDto;
import ma.ibsys.ibsysretailmanager.invoice.InvoiceItem;
import ma.ibsys.ibsysretailmanager.invoice.InvoiceItemDto;
import ma.ibsys.ibsysretailmanager.product.Product;
import ma.ibsys.ibsysretailmanager.product.ProductRepository;
import ma.ibsys.ibsysretailmanager.product.ProductRequestDto;
import ma.ibsys.ibsysretailmanager.product.ProductResponseDto;
import ma.ibsys.ibsysretailmanager.user.User;
import ma.ibsys.ibsysretailmanager.user.UserCreateDto;
import ma.ibsys.ibsysretailmanager.user.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ModelMapperConfig {
  private final CustomerRepository customerRepository;
  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();

    // -- Customer ------------------------------------------------------------

    // map CustomerCreateDto to Customer entity
    modelMapper
        .createTypeMap(CustomerCreateDto.class, Customer.class)
        .addMappings(mapper -> mapper.map(CustomerCreateDto::getIce, Customer::setICE))
        .addMappings(mapper -> mapper.map(CustomerCreateDto::getName, Customer::setName))
        .addMappings(mapper -> mapper.map(CustomerCreateDto::getEmail, Customer::setEmail))
        .addMappings(mapper -> mapper.map(CustomerCreateDto::getPhone, Customer::setPhone))
        .addMappings(mapper -> mapper.map(CustomerCreateDto::getAddress, Customer::setAddress));

    // map CustomerUpdateDto to Customer entity
    modelMapper
        .createTypeMap(CustomerUpdateDto.class, Customer.class)
        .addMappings(mapper -> mapper.map(CustomerUpdateDto::getName, Customer::setName))
        .addMappings(mapper -> mapper.map(CustomerUpdateDto::getEmail, Customer::setEmail))
        .addMappings(mapper -> mapper.map(CustomerUpdateDto::getPhone, Customer::setPhone))
        .addMappings(mapper -> mapper.map(CustomerUpdateDto::getAddress, Customer::setAddress));

    // map Customer entity to CustomerResponseDto
    modelMapper
        .createTypeMap(Customer.class, CustomerResponseDto.class)
        .addMappings(mapper -> mapper.map(Customer::getICE, CustomerResponseDto::setIce))
        .addMappings(mapper -> mapper.map(Customer::getName, CustomerResponseDto::setName))
        .addMappings(mapper -> mapper.map(Customer::getEmail, CustomerResponseDto::setEmail))
        .addMappings(mapper -> mapper.map(Customer::getPhone, CustomerResponseDto::setPhone))
        .addMappings(mapper -> mapper.map(Customer::getAddress, CustomerResponseDto::setAddress));

    // -- Product -------------------------------------------------------------

    // map ProductRequestDto to Product entity
    modelMapper
        .createTypeMap(ProductRequestDto.class, Product.class)
        .addMappings(mapper -> mapper.map(ProductRequestDto::getBarCode, Product::setBarCode))
        .addMappings(mapper -> mapper.map(ProductRequestDto::getName, Product::setName))
        .addMappings(mapper -> mapper.map(ProductRequestDto::getTaxRate, Product::setTaxRate))
        .addMappings(mapper -> mapper.map(ProductRequestDto::getCategory, Product::setCategory))
        .addMappings(
            mapper ->
                mapper.map(
                    ProductRequestDto::getSellingPriceExcludingTax,
                    Product::setSellingPriceExcludingTax))
        .addMappings(
            mapper -> mapper.map(ProductRequestDto::getPurchasePrice, Product::setPurchasePrice))
        .setPostConverter(
            context -> {
              ProductRequestDto source = context.getSource();
              Product destination = context.getDestination();

              Category category =
                  categoryRepository
                      .findById(source.getCategory())
                      .orElseThrow(
                          () ->
                              new EntityNotFoundException(
                                  "Categorie introuvable avec l'ID " + source.getCategory()));

              destination.setCategory(category);

              return destination;
            });

    // map Product entity to ProductResponseDto
    modelMapper
        .createTypeMap(Product.class, ProductResponseDto.class)
        .addMappings(mapper -> mapper.map(Product::getId, ProductResponseDto::setId))
        .addMappings(mapper -> mapper.map(Product::getBarCode, ProductResponseDto::setBarCode))
        .addMappings(mapper -> mapper.map(Product::getName, ProductResponseDto::setName))
        .addMappings(mapper -> mapper.map(Product::getTaxRate, ProductResponseDto::setTaxRate))
        .addMappings(
            mapper -> mapper.map(src -> src.getCategory().getId(), ProductResponseDto::setCategory))
        .addMappings(
            mapper ->
                mapper.map(
                    Product::getSellingPriceExcludingTax,
                    ProductResponseDto::setSellingPriceExcludingTax))
        .addMappings(
            mapper -> mapper.map(Product::getPurchasePrice, ProductResponseDto::setPurchasePrice));

    // -- Category ---------------------------------------------------------

    // Map CategoryCreateDto to Category entity
    modelMapper
        .createTypeMap(CategoryCreateDto.class, Category.class)
        .addMappings(mapper -> mapper.map(CategoryCreateDto::getName, Category::setName))
        .addMappings(
            mapper -> mapper.map(CategoryCreateDto::getDescription, Category::setDescription));

    // -- Invoice -------------------------------------------------------------

    // Map InvoiceCreateDto to Invoice entity
    modelMapper
        .createTypeMap(InvoiceCreateDto.class, Invoice.class)
        .addMappings(
            mapper -> {
              mapper.skip(Invoice::setId);
              mapper.skip(Invoice::setItems);
            })
        .setPostConverter(
            context -> {
              InvoiceCreateDto source = context.getSource();

              List<InvoiceItem> items =
                  source.getItems().stream()
                      .map(
                          itemDto -> {
                            InvoiceItem invoiceItem = modelMapper.map(itemDto, InvoiceItem.class);

                            Product product =
                                productRepository
                                    .findById(itemDto.getProductId())
                                    .orElseThrow(
                                        () ->
                                            new EntityNotFoundException(
                                                "Produit introuvable avec l'ICE "
                                                    + itemDto.getProductId()));

                            invoiceItem.setProduct(product);
                            return invoiceItem;
                          })
                      .collect(Collectors.toList());

              Customer customer =
                  customerRepository
                      .findByICE(source.getCustomerICE())
                      .orElseThrow(
                          () ->
                              new EntityNotFoundException(
                                  "Client introuvable avec l'ICE " + source.getCustomerICE()));

              Invoice newInvoice =
                  Invoice.builder()
                      .customer(customer)
                      .issueDate(LocalDate.now())
                      .items(new ArrayList<>())
                      .build();

              for (InvoiceItem item : items) {
                newInvoice.addItem(item);
              }

              return newInvoice;
            });

    // -- InvoiceItem ---------------------------------------------------------

    // Map InvoiceItemDto to InvoiceItem entity
    modelMapper
        .createTypeMap(InvoiceItemDto.class, InvoiceItem.class)
            .addMappings(
                    mapper -> {
                        mapper.skip(InvoiceItem::setId);
                    })
            .addMappings(mapper -> mapper.map(InvoiceItemDto::getUnitPrice, InvoiceItem::setUnitPrice))
            .addMappings(mapper -> mapper.map(InvoiceItemDto::getQuantity, InvoiceItem::setQuantity));

    // Map InvoiceItem entity to InvoiceItemDto
    modelMapper
        .createTypeMap(InvoiceItem.class, InvoiceItemDto.class)
        .addMappings(
            mapper -> mapper.map(src -> src.getProduct().getId(), InvoiceItemDto::setProductId))
        .addMappings(mapper -> mapper.map(InvoiceItem::getUnitPrice, InvoiceItemDto::setUnitPrice))
        .addMappings(mapper -> mapper.map(InvoiceItem::getQuantity, InvoiceItemDto::setQuantity));

    // -- User ----------------------------------------------------------------

    // Map UserCreateDto to User
    modelMapper
        .createTypeMap(UserCreateDto.class, User.class)
        .addMapping(UserCreateDto::getFirstName, User::setFirstName)
        .addMapping(UserCreateDto::getLastName, User::setLastName)
        .addMapping(UserCreateDto::getEmail, User::setEmail)
        .addMapping(UserCreateDto::getPassword, User::setPassword)
        .addMapping(UserCreateDto::getRole, User::setRole);

    // Map User to UserDto
    modelMapper
        .createTypeMap(User.class, UserDto.class)
        .addMapping(User::getId, UserDto::setId)
        .addMapping(User::getFirstName, UserDto::setFirstName)
        .addMapping(User::getLastName, UserDto::setLastName)
        .addMapping(User::getEmail, UserDto::setEmail)
        .addMapping(User::getRole, UserDto::setRole);

    return modelMapper;
  }
}
