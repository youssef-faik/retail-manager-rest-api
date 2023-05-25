package ma.ibsys.ibsysretailmanager.customer;

import jakarta.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import ma.ibsys.ibsysretailmanager.exceptions.BadRequestException;
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

  public ResponseEntity<CustomerResponseDto> getCustomerByICE(String ice) {
    Customer customer =
        customerRepository
            .findByICE(ice)
            .orElseThrow(() -> new EntityNotFoundException("Client introuvable avec l'ICE " + ice));
    return ResponseEntity.ok(modelMapper.map(customer, CustomerResponseDto.class));
  }

  public ResponseEntity<Void> createCustomer(CustomerCreateDto customerCreateDto) {
    if (customerRepository.findByICE(customerCreateDto.getIce()).isPresent()) {
      throw new BadRequestException(
          "Le client avec l'ICE " + customerCreateDto.getIce() + " existe déjà.");
    }

    if (customerRepository.findByNameLikeIgnoreCase(customerCreateDto.getName()).isPresent()) {
      throw new BadRequestException(
          "Un client avec le nom " + customerCreateDto.getName() + " existe déjà.");
    }

    Customer customer = modelMapper.map(customerCreateDto, Customer.class);
    Customer savedCustomer = customerRepository.save(customer);

    return ResponseEntity.created(URI.create("/api/v1/customers/" + savedCustomer.getICE()))
        .build();
  }

  public ResponseEntity<CustomerResponseDto> updateCustomer(
      String ice, CustomerUpdateDto customerUpdateDto) {
    Customer customer =
        customerRepository
            .findByICE(ice)
            .orElseThrow(() -> new EntityNotFoundException("Client introuvable avec l'ICE " + ice));

    if ((!customer.getName().equalsIgnoreCase(customerUpdateDto.getName()))
        && (customerRepository.countCustomerByName(customerUpdateDto.getName()) > 0)) {
      throw new BadRequestException(
          "Un client avec le nom " + customerUpdateDto.getName() + " existe déjà.");
    }

    customer.setAddress(customerUpdateDto.getAddress());
    customer.setName(customerUpdateDto.getName());
    customer.setEmail(customerUpdateDto.getEmail());
    customer.setPhone(customerUpdateDto.getPhone());

    Customer updatedCustomer = customerRepository.save(customer);
    return ResponseEntity.ok(modelMapper.map(updatedCustomer, CustomerResponseDto.class));
  }

  public ResponseEntity<Void> deleteCustomerByICE(String ice) {
    Customer customer =
        customerRepository
            .findByICE(ice)
            .orElseThrow(() -> new EntityNotFoundException("Client introuvable avec l'ICE " + ice));
    customerRepository.delete(customer);
    return ResponseEntity.noContent().build();
  }
}
