package ma.ibsys.ibsysretailmanager.shared;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import ma.ibsys.ibsysretailmanager.customer.Customer;
import ma.ibsys.ibsysretailmanager.customer.CustomerRepository;
import ma.ibsys.ibsysretailmanager.customer.CustomerRequestDto;
import ma.ibsys.ibsysretailmanager.customer.CustomerResponseDto;
import ma.ibsys.ibsysretailmanager.invoice.Invoice;
import ma.ibsys.ibsysretailmanager.invoice.InvoiceCreateDto;
import ma.ibsys.ibsysretailmanager.invoice.InvoiceDto;
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

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();

    // -- Customer ------------------------------------------------------------

    // map CustomerRequestDto to Customer entity
    modelMapper
        .createTypeMap(CustomerRequestDto.class, Customer.class)
        .addMappings(mapper -> mapper.map(CustomerRequestDto::getName, Customer::setName))
        .addMappings(mapper -> mapper.map(CustomerRequestDto::getEmail, Customer::setEmail))
        .addMappings(mapper -> mapper.map(CustomerRequestDto::getPhone, Customer::setPhone))
        .addMappings(mapper -> mapper.map(CustomerRequestDto::getAddress, Customer::setAddress));

    // map Customer entity to CustomerResponseDto
    modelMapper
        .createTypeMap(Customer.class, CustomerResponseDto.class)
        .addMappings(mapper -> mapper.map(Customer::getId, CustomerResponseDto::setId))
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
        .addMappings(
            mapper ->
                mapper.map(ProductRequestDto::getPriceExcludingTax, Product::setPriceExcludingTax))
        .addMappings(mapper -> mapper.map(ProductRequestDto::getTaxRate, Product::setTaxRate));

    // map Product entity to ProductResponseDto
    modelMapper
        .createTypeMap(Product.class, ProductResponseDto.class)
        .addMappings(mapper -> mapper.map(Product::getId, ProductResponseDto::setId))
        .addMappings(mapper -> mapper.map(Product::getBarCode, ProductResponseDto::setBarCode))
        .addMappings(mapper -> mapper.map(Product::getName, ProductResponseDto::setName))
        .addMappings(
            mapper ->
                mapper.map(Product::getPriceExcludingTax, ProductResponseDto::setPriceExcludingTax))
        .addMappings(mapper -> mapper.map(Product::getTaxRate, ProductResponseDto::setTaxRate));

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
              Invoice destination = context.getDestination();

              List<InvoiceItem> items =
                  source.getItems().stream()
                      .map(itemDto -> modelMapper.map(itemDto, InvoiceItem.class))
                      .collect(Collectors.toList());
              destination.setItems(items);

              Customer customer =
                  customerRepository
                      .findById(source.getCustomerId())
                      .orElseThrow(
                          () ->
                              new EntityNotFoundException(
                                  "Customer not found with id: " + source.getCustomerId()));
              destination.setCustomer(customer);

              return destination;
            });

    // Map Invoice entity to InvoiceDto
    modelMapper
        .createTypeMap(Invoice.class, InvoiceDto.class)
        .addMappings(
            mapper -> mapper.map(src -> src.getCustomer().getId(), InvoiceDto::setCustomerId))
        .setPostConverter(
            context -> {
              Invoice source = context.getSource();
              InvoiceDto destination = context.getDestination();
              List<InvoiceItemDto> items =
                  source.getItems().stream()
                      .map(item -> modelMapper.map(item, InvoiceItemDto.class))
                      .collect(Collectors.toList());
              destination.setItems(items);
              return destination;
            });

    // -- InvoiceItem ---------------------------------------------------------
    
    // Map InvoiceItemDto to InvoiceItem entity
    modelMapper
        .createTypeMap(InvoiceItemDto.class, InvoiceItem.class)
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
