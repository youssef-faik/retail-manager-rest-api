package ma.ibsys.ibsysretailmanager.customer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/customers")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(
    name = "Customer",
    description =
        "The Customer API. Contains all the operations that can be performed on a customer")
public interface CustomerApi {
  @GetMapping
  @Operation(
      summary = "Get all customers",
      description = "Get a list that contains the details for all customers.")
  ResponseEntity<List<CustomerResponseDto>> getAllCustomers();

  @GetMapping("/{id}")
  @Operation(
      summary = "Get customer details",
      description = "Get the details of the customer with the given id.")
  ResponseEntity<CustomerResponseDto> getCustomer(@PathVariable Long id);

  @PostMapping
  @Operation(
      summary = "Create customer",
      description = "Create a new customer with the given details.")
  ResponseEntity<CustomerResponseDto> createCustomer(
      @Valid @RequestBody CustomerRequestDto customerRequestDto);

  @PutMapping("/{id}")
  @Operation(
      summary = "Update customer details",
      description = "Update the details of the customer with given id.")
  ResponseEntity<CustomerResponseDto> updateCustomer(
      @PathVariable Long id, @Valid @RequestBody CustomerRequestDto customerRequestDto);

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete customer", description = "Delete a customer with given id.")
  ResponseEntity<Void> deleteCustomer(@PathVariable Long id);
}
