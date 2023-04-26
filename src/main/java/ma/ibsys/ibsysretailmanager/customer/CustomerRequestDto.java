package ma.ibsys.ibsysretailmanager.customer;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(title = "Customer Request Schema", description = "Request body for creating/updating a customer")
public class CustomerRequestDto {
  @Schema(description = "Name of the customer", example = "Rona SARL", required = true)
  @Size(min = 2, max = 100, message = "name must be between {min} and {max} characters long")
  @NotBlank(message = "name is mandatory")
  private String name;

  @Schema(
      description = "Email address of the customer",
      example = "rona-sarl@example.com",
      required = true)
  @Email(message = "email should be valid")
  @NotBlank(message = "email is mandatory")
  @Column(name = "email", nullable = false)
  private String email;

  @Schema(description = "Phone number of the customer", example = "1234567890", required = true)
  @Size(
      min = 10,
      max = 10,
      message = "phone number must be between {min} and {max} characters long")
  @NotBlank(message = "phone number is mandatory")
  private String phone;

  @Schema(
      description = "Address of the customer",
      example = "123 Main St, City, State, Zip",
      required = true)
  @Size(min = 10, max = 500, message = "address must be between {min} and {max} characters long")
  @NotBlank(message = "address is mandatory")
  private String address;
}
