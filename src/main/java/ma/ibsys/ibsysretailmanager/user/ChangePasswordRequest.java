package ma.ibsys.ibsysretailmanager.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        title = "Change Password Request Schema",
        description = "Request body for changing the user's password.")
public class ChangePasswordRequest {

  @Schema(description = "User's old password.", required = true)
  @Size(min = 8, message = "The old password must be between {min} and {max} characters.")
  @NotBlank(message = "oldPassword is required.")
  private String oldPassword;

  @Schema(description = "User's new password.", required = true)
  @Size(min = 8, message = "The new password must be between {min} and {max} characters.")
  @NotBlank(message = "newPassword is required.")
  private String newPassword;
}
