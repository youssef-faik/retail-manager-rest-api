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
  public ResponseEntity<CustomerResponseDto> getCustomer(@PathVariable String ice) {
    return customerService.getCustomerByICE(ice);
  }

  @Override
  public ResponseEntity<Void> createCustomer(
      @Valid @RequestBody CustomerCreateDto customerCreateDto) {
    return customerService.createCustomer(customerCreateDto);
  }

  @Override
  public ResponseEntity<CustomerResponseDto> updateCustomer(
      @PathVariable String ice, @Valid @RequestBody CustomerUpdateDto customerUpdateDto) {
    return customerService.updateCustomer(ice, customerUpdateDto);
  }

  @Override
  public ResponseEntity<Void> deleteCustomer(@PathVariable String ice) {
    return customerService.deleteCustomerByICE(ice);
  }
}
