package ma.ibsys.ibsysretailmanager.customer;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
  private final CustomerService customerService;

  @GetMapping
  public ResponseEntity<List<CustomerResponseDto>> getAllCustomers() {
    List<CustomerResponseDto> customers = customerService.getAllCustomers();
    return ResponseEntity.ok(customers);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CustomerResponseDto> getCustomer(@PathVariable Long id) {
    CustomerResponseDto customer = customerService.getCustomerById(id);
    return ResponseEntity.ok(customer);
  }

  @PostMapping
  public ResponseEntity<CustomerResponseDto> createCustomer(
      @Valid @RequestBody CustomerRequestDto customerRequestDto) {
    CustomerResponseDto createdCustomer = customerService.createCustomer(customerRequestDto);
    return ResponseEntity.created(URI.create("/api/customers/" + createdCustomer.getId()))
        .body(createdCustomer);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CustomerResponseDto> updateCustomer(
      @PathVariable Long id, @Valid @RequestBody CustomerRequestDto customerRequestDto) {
    CustomerResponseDto updatedCustomer = customerService.updateCustomer(id, customerRequestDto);
    return ResponseEntity.ok(updatedCustomer);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
    customerService.deleteCustomerById(id);
    return ResponseEntity.noContent().build();
  }
}
