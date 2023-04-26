package ma.ibsys.ibsysretailmanager.customer;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerController implements CustomerApi {
  private final CustomerService customerService;

  @Override
  public ResponseEntity<List<CustomerResponseDto>> getAllCustomers() {
    return customerService.getAllCustomers();
  }

  @Override
  public ResponseEntity<CustomerResponseDto> getCustomer(@PathVariable Long id) {
    return customerService.getCustomerById(id);
  }

  @Override
  public ResponseEntity<Void> createCustomer(
      @Valid @RequestBody CustomerRequestDto customerRequestDto) {
    return customerService.createCustomer(customerRequestDto);
  }

  @Override
  public ResponseEntity<CustomerResponseDto> updateCustomer(
      @PathVariable Long id, @Valid @RequestBody CustomerRequestDto customerRequestDto) {
    return customerService.updateCustomer(id, customerRequestDto);
  }

  @Override
  public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
    return customerService.deleteCustomerById(id);
  }
}
