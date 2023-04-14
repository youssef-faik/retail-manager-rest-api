package ma.ibsys.ibsysretailmanager.customer;

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
public class CustomerRequestDto {
  @Size(min = 2, max = 100, message = "name must be between {min} and {max} characters long")
  @NotBlank(message = "name is mandatory")
  private String name;

  @Email(message = "email should be valid")
  @NotBlank(message = "email is mandatory")
  @Column(name = "email", nullable = false)
  private String email;

  @Size(
      min = 10,
      max = 10,
      message = "phone number must be between {min} and {max} characters long")
  @NotBlank(message = "phone number is mandatory")
  private String phone;

  @Size(min = 10, max = 500, message = "address must be between {min} and {max} characters long")
  @NotBlank(message = "address is mandatory")
  private String address;
}
