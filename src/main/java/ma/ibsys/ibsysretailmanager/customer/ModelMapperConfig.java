package ma.ibsys.ibsysretailmanager.customer;

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
               .addMappings(mapper -> mapper.map(CustomerRequestDto::getPhone, Customer::setPhone));
    
    // map Customer entity to CustomerResponseDto
    modelMapper.createTypeMap(Customer.class, CustomerResponseDto.class)
               .addMappings(mapper -> mapper.map(Customer::getId, CustomerResponseDto::setId))
               .addMappings(mapper -> mapper.map(Customer::getName, CustomerResponseDto::setName))
               .addMappings(mapper -> mapper.map(Customer::getEmail, CustomerResponseDto::setEmail))
               .addMappings(mapper -> mapper.map(Customer::getPhone, CustomerResponseDto::setPhone))
               .addMappings(mapper -> mapper.map(Customer::getAddress, CustomerResponseDto::setAddress));
    
    return modelMapper;
  }
}
