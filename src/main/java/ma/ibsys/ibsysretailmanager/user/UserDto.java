package ma.ibsys.ibsysretailmanager.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "User Response Schema", description = "Response body for user details.")
public class UserDto {
  @Schema(description = "ID of the user.", example = "2")
  private int id;

  @Schema(description = "First name of the user.", example = "Said")
  private String firstName;

  @Schema(description = "Last name of the user.", example = "Alami")
  private String lastName;

  @Schema(description = "Email address of the user.", example = "said.alami@example.com")
  private String email;

  @Schema(description = "Role of the user.", example = "MANAGER")
  private Role role;
}
