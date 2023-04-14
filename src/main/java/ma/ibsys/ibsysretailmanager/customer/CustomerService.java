package ma.ibsys.ibsysretailmanager.customer;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
  private final CustomerRepository customerRepository;
  private final ModelMapper modelMapper;

  public List<CustomerResponseDto> getAllCustomers() {
    List<Customer> customers = customerRepository.findAll();
    return customers.stream()
        .map(customer -> modelMapper.map(customer, CustomerResponseDto.class))
        .collect(Collectors.toList());
  }

  public CustomerResponseDto getCustomerById(Long id) {
    Customer customer =
        customerRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Customer not found with id " + id));
    return modelMapper.map(customer, CustomerResponseDto.class);
  }

  public CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto) {
    Customer customer = modelMapper.map(customerRequestDto, Customer.class);
    Customer savedCustomer = customerRepository.save(customer);
    return modelMapper.map(savedCustomer, CustomerResponseDto.class);
  }

  public CustomerResponseDto updateCustomer(Long id, CustomerRequestDto customerRequestDto) {
    Customer customer =
        customerRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Customer not found with id " + id));
    customer.setName(customerRequestDto.getName());
    customer.setEmail(customerRequestDto.getEmail());
    customer.setPhone(customerRequestDto.getPhone());
    Customer updatedCustomer = customerRepository.save(customer);
    return modelMapper.map(updatedCustomer, CustomerResponseDto.class);
  }

  public void deleteCustomerById(Long id) {
    Customer customer =
        customerRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));
    customerRepository.delete(customer);
  }
}
