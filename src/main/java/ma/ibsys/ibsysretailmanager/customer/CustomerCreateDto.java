package ma.ibsys.ibsysretailmanager.customer;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
@Schema(
        title = "Schema for Customer Creation Request",
        description = "Request body for creating a customer")
public class CustomerCreateDto {
  @Schema(description = "Customer's ICE", example = "563456789123456", required = true)
  @NotBlank(message = "ICE is required")
  @Pattern(
          regexp = "^\\d{15}$",
          message =
                  "The ICE number must have exactly 15 characters and consist only of digits")
  private String ice;

  @Schema(description = "Customer's name", example = "Acme Corp", required = true)
  @Size(min = 2, max = 100, message = "The name must be between {min} and {max} characters")
  @NotBlank(message = "Name is required")
  private String name;

  @Schema(
          description = "Customer's email address",
          example = "acme-corp@example.com",
          required = true)
  @Email(message = "The email must be valid")
  @Column(name = "email", nullable = false)
  private String email;

  @Schema(description = "Customer's phone number", example = "0522567890", required = true)
  @NotNull(message = "Phone number is required")
  @Pattern(
          regexp = "^0\\d{9}$",
          message =
                  "The phone number must have exactly 10 digits and consist only of digits")
  private String phone;

  @Schema(
          description = "Customer's address",
          example = "123 Main Street, City, State, Postal Code",
          required = true)
  @Size(min = 10, max = 500, message = "The address must be between {min} and {max} characters")
  @NotBlank(message = "Address is required")
  private String address;
}
