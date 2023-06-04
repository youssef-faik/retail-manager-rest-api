package ma.ibsys.ibsysretailmanager.configuration;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppConfigurationController implements AppConfigurationApi {
  AppConfiguration appConfiguration = AppConfiguration.getInstance();

  @Override
  public List<ConfigOption> getAllConfigurations() {
    return appConfiguration.getAllConfigurations();
  }

  @Override
  public ConfigOption getConfigurationValue(@PathVariable ConfigKey key) {
    return appConfiguration.getConfigurationValue(key);
  }

  @Override
  public void setConfigurationValues(@RequestBody List<ConfigOptionDto> configOptionDTOs) {
    appConfiguration.setConfigurationValues(configOptionDTOs);
  }
}
