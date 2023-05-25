package ma.ibsys.ibsysretailmanager.user;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Rôle", description = "Énumération des rôles utilisateur")
public enum Role {
  @Schema(name = "Le rôle administrateur")
  ADMIN,
  @Schema(name = "Le rôle gestionnaire")
  MANAGER,
  @Schema(name = "Le rôle caissier")
  CASHIER
}
