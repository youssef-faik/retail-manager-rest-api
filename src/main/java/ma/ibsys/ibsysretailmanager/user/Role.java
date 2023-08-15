package ma.ibsys.ibsysretailmanager.user;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Role", description = "Enumeration of user roles")
public enum Role {
  @Schema(name = "Administrator role")
  ADMIN,
  @Schema(name = "Manager role")
  MANAGER,
  @Schema(name = "Cashier role")
  CASHIER
}
