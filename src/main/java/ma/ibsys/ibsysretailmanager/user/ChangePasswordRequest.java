package ma.ibsys.ibsysretailmanager.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(title = "Change Password Request Schema", description = "Request body for changing the password")
public class ChangePasswordRequest {
  
  @Schema(description = "The user's old password.", required = true)
  @Size(min = 8, message = "oldPassword must be between {min} and {max} characters long")
  @NotBlank(message = "oldPassword is mandatory")
  private String oldPassword;
  
  @Schema(description = "The user's new password.", required = true)
  @Size(min = 8, message = "newPassword must be between {min} and {max} characters long")
  @NotBlank(message = "newPassword is mandatory")
  private String newPassword;
}
