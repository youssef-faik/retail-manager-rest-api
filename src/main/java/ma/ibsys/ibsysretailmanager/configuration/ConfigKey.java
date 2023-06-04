package ma.ibsys.ibsysretailmanager.configuration;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ConfigKey", description = "Énumération des options de configuration.")
public enum ConfigKey {
  LAST_INVOICE_NUMBER,
  LAST_BL_NUMBER
}
