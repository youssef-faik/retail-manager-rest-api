package ma.ibsys.ibsysretailmanager.shared;

import ma.ibsys.ibsysretailmanager.customer.Customer;
import ma.ibsys.ibsysretailmanager.customer.CustomerRequestDto;
import ma.ibsys.ibsysretailmanager.customer.CustomerResponseDto;
import ma.ibsys.ibsysretailmanager.product.Product;
import ma.ibsys.ibsysretailmanager.product.ProductRequestDto;
import ma.ibsys.ibsysretailmanager.product.ProductResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
  
  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    
    // map CustomerRequestDto to Customer entity
    modelMapper.createTypeMap(CustomerRequestDto.class, Customer.class)
               .addMappings(mapper -> mapper.map(CustomerRequestDto::getName, Customer::setName))
               .addMappings(mapper -> mapper.map(CustomerRequestDto::getEmail, Customer::setEmail))
               .addMappings(mapper -> mapper.map(CustomerRequestDto::getPhone, Customer::setPhone))
               .addMappings(mapper -> mapper.map(CustomerRequestDto::getAddress, Customer::setAddress));
    
    // map Customer entity to CustomerResponseDto
    modelMapper.createTypeMap(Customer.class, CustomerResponseDto.class)
               .addMappings(mapper -> mapper.map(Customer::getId, CustomerResponseDto::setId))
               .addMappings(mapper -> mapper.map(Customer::getName, CustomerResponseDto::setName))
               .addMappings(mapper -> mapper.map(Customer::getEmail, CustomerResponseDto::setEmail))
               .addMappings(mapper -> mapper.map(Customer::getPhone, CustomerResponseDto::setPhone))
               .addMappings(mapper -> mapper.map(Customer::getAddress, CustomerResponseDto::setAddress));
  
    // map ProductRequestDto to Product entity
    modelMapper.createTypeMap(ProductRequestDto.class, Product.class)
               .addMappings(mapper -> mapper.map(ProductRequestDto::getBarCode, Product::setBarCode))
               .addMappings(mapper -> mapper.map(ProductRequestDto::getPriceExcludingTax, Product::setPriceExcludingTax))
               .addMappings(mapper -> mapper.map(ProductRequestDto::getTaxRate, Product::setTaxRate));
  
    // map Product entity to ProductResponseDto
    modelMapper.createTypeMap(Product.class, ProductResponseDto.class)
               .addMappings(mapper -> mapper.map(Product::getId, ProductResponseDto::setId))
               .addMappings(mapper -> mapper.map(Product::getBarCode, ProductResponseDto::setBarCode))
               .addMappings(mapper -> mapper.map(Product::getPriceExcludingTax, ProductResponseDto::setPriceExcludingTax))
               .addMappings(mapper -> mapper.map(Product::getTaxRate, ProductResponseDto::setTaxRate));
  
    return modelMapper;
  }
}
