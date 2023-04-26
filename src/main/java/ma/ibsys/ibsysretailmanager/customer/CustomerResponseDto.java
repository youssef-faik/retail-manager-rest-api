package ma.ibsys.ibsysretailmanager.customer;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(title = "Customer Response Schema", description = "Response body for a customer response")
public class CustomerResponseDto {
  @Schema(description = "ID of the customer", example = "1")
  private Long id;

  @Schema(description = "Name of the customer", example = "Rona SARL", required = true)
  private String name;

  @Schema(
      description = "Email address of the customer",
      example = "rona-sarl@example.com",
      required = true)
  private String email;

  @Schema(description = "Phone number of the customer", example = "1234567890", required = true)
  private String phone;

  @Schema(
      description = "Address of the customer",
      example = "123 Main St, City, State, Zip",
      required = true)
  private String address;
}
