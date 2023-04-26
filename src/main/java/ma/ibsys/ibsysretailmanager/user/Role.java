package ma.ibsys.ibsysretailmanager.user;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Role", description = "Enumeration of user roles")
public enum Role {
  @Schema(name = "The administrator role")
  ADMIN,
  @Schema(name = "The manager role")
  MANAGER,
  @Schema(name = "The cashier role")
  CASHIER
}
