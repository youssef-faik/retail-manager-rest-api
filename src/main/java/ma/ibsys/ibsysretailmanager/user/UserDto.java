package ma.ibsys.ibsysretailmanager.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        title = "User Response Schema",
        description = "Response body for user details.")
public class UserDto {
  @Schema(description = "User ID.", example = "2")
  private int id;

  @Schema(description = "User's first name.", example = "Said")
  private String firstName;

  @Schema(description = "User's last name.", example = "Alami")
  private String lastName;

  @Schema(description = "User's email address.", example = "said.alami@example.com")
  private String email;

  @Schema(description = "User's role.", example = "MANAGER")
  private Role role;
}
