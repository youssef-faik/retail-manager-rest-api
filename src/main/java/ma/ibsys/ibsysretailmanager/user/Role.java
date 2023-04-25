package ma.ibsys.ibsysretailmanager.user;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "The user role")
public enum Role {
  @Schema(description = "The administrator role")
  ADMIN,
  @Schema(description = "The manager role")
  MANAGER,
  @Schema(description = "The cashier role")
  CASHIER
}
