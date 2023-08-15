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
@Schema(title = "Schema for Customer Response.", description = "Response body for a customer.")
public class CustomerResponseDto {
  @Schema(description = "Customer's ICE.", example = "563456789123456", required = true)
  private String ice;

  @Schema(description = "Customer's name.", example = "Acme Corp", required = true)
  private String name;

  @Schema(
          description = "Customer's email address.",
          example = "acme-corp@example.com",
          required = true)
  private String email;

  @Schema(description = "Customer's phone number.", example = "0522567890", required = true)
  private String phone;

  @Schema(
          description = "Customer's address.",
          example = "123 Main Street, City, State, Postal Code.",
          required = true)
  private String address;
}
