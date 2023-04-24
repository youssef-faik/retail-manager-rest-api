package ma.ibsys.ibsysretailmanager.customer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
@SecurityRequirement(name = "Bearer Authentication")
@Tag(
    name = "Customer",
    description =
        "The Customer API. Contains all the operations that can be performed on a customer")
public class CustomerController {
  private final CustomerService customerService;

  @GetMapping
  @Operation(
      summary = "Get all customers",
      description = "Get a list that contains the details for all customers.")
  public ResponseEntity<List<CustomerResponseDto>> getAllCustomers() {
    return customerService.getAllCustomers();
  }

  @GetMapping("/{id}")
  @Operation(
      summary = "Get customer details",
      description = "Get the details of the customer with the given id.")
  public ResponseEntity<CustomerResponseDto> getCustomer(@PathVariable Long id) {
    return customerService.getCustomerById(id);
  }

  @PostMapping
  @Operation(
      summary = "Create customer",
      description = "Create a new customer with the given details.")
  public ResponseEntity<CustomerResponseDto> createCustomer(
      @Valid @RequestBody CustomerRequestDto customerRequestDto) {
    return customerService.createCustomer(customerRequestDto);
  }

  @PutMapping("/{id}")
  @Operation(
      summary = "Update customer details",
      description = "Update the details of the customer with given id.")
  public ResponseEntity<CustomerResponseDto> updateCustomer(
      @PathVariable Long id, @Valid @RequestBody CustomerRequestDto customerRequestDto) {
    return customerService.updateCustomer(id, customerRequestDto);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete customer", description = "Delete a customer with given id.")
  public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
    return customerService.deleteCustomerById(id);
  }
}
