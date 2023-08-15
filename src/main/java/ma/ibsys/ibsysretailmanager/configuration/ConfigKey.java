package ma.ibsys.ibsysretailmanager.configuration;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ConfigKey", description = "Enumeration of configuration options.")
public enum ConfigKey {
  NEXT_INVOICE_NUMBER,
}
