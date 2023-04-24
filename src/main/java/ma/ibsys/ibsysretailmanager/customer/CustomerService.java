package ma.ibsys.ibsysretailmanager.customer;

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
public class CustomerService {
  private final CustomerRepository customerRepository;
  private final ModelMapper modelMapper;

  public ResponseEntity<List<CustomerResponseDto>> getAllCustomers() {
    List<CustomerResponseDto> customers =
        customerRepository.findAll().stream()
            .map(customer -> modelMapper.map(customer, CustomerResponseDto.class))
            .collect(Collectors.toList());
    return ResponseEntity.ok(customers);
  }

  public ResponseEntity<CustomerResponseDto> getCustomerById(Long id) {
    Customer customer =
        customerRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Customer not found with id " + id));
    return ResponseEntity.ok(modelMapper.map(customer, CustomerResponseDto.class));
  }

  public ResponseEntity<CustomerResponseDto> createCustomer(CustomerRequestDto customerRequestDto) {
    Customer customer = modelMapper.map(customerRequestDto, Customer.class);
    Customer savedCustomer = customerRepository.save(customer);

    return ResponseEntity.created(URI.create("/api/customers/" + savedCustomer.getId())).build();
  }

  public ResponseEntity<CustomerResponseDto> updateCustomer(
      Long id, CustomerRequestDto customerRequestDto) {
    Customer customer =
        customerRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Customer not found with id " + id));
    customer.setName(customerRequestDto.getName());
    customer.setEmail(customerRequestDto.getEmail());
    customer.setPhone(customerRequestDto.getPhone());
    Customer updatedCustomer = customerRepository.save(customer);
    return ResponseEntity.ok(modelMapper.map(updatedCustomer, CustomerResponseDto.class));
  }

  public ResponseEntity<Void> deleteCustomerById(Long id) {
    Customer customer =
        customerRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));
    customerRepository.delete(customer);
    return ResponseEntity.noContent().build();
  }
}
