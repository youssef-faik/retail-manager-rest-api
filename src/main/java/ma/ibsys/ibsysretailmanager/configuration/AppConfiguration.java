package ma.ibsys.ibsysretailmanager.configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ma.ibsys.ibsysretailmanager.exceptions.BadRequestException;
import ma.ibsys.ibsysretailmanager.invoice.Invoice;
import ma.ibsys.ibsysretailmanager.invoice.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppConfiguration {
  private static ConfigOptionRepository configOptionRepository;
  private static InvoiceRepository invoiceRepository;
  private static AppConfiguration instance;

  @Autowired
  protected AppConfiguration(
          ConfigOptionRepository configOptionRepository, InvoiceRepository invoiceRepository) {
    AppConfiguration.configOptionRepository = configOptionRepository;
    AppConfiguration.invoiceRepository = invoiceRepository;

    if (configOptionRepository.findByKey(ConfigKey.NEXT_INVOICE_NUMBER).isEmpty()) {
      ConfigOption nextInvoiceNumber =
              ConfigOption.builder().key(ConfigKey.NEXT_INVOICE_NUMBER).value("1").build();
      AppConfiguration.configOptionRepository.save(nextInvoiceNumber);
    }
  }

  public static AppConfiguration getInstance() {
    if (instance == null) {
      synchronized (AppConfiguration.class) {
        if (instance == null) {
          instance = new AppConfiguration(configOptionRepository, invoiceRepository);
        }
      }
    }
    return instance;
  }

  private static void validateNextInvoiceNumber(int nextInvoiceNumber) {
    if (nextInvoiceNumber <= 0) {
      throw new BadRequestException(
              "The next invoice number must be strictly greater than 0.");
    }

    if (invoiceRepository.count() > 0) {
      Invoice invoice = invoiceRepository.findFirstByOrderByIdDesc().orElseThrow();
      if (nextInvoiceNumber <= invoice.getId()) {
        throw new BadRequestException(
                "The next invoice number must be greater than or equal to " + (invoice.getId() + 1));
      }
    }
  }

  private static void updateOptionValue(ConfigKey key, String value) {
    ConfigOption option =
            configOptionRepository
                    .findByKey(key)
                    .orElseThrow(
                            () ->
                                    new RuntimeException(
                                            "Option not found with key " + ConfigKey.NEXT_INVOICE_NUMBER.name()));

    option.setValue(value);
    configOptionRepository.save(option);
  }

  private static void updateNextInvoiceNumber(int nextInvoiceNumber) {
    validateNextInvoiceNumber(nextInvoiceNumber);
    updateOptionValue(ConfigKey.NEXT_INVOICE_NUMBER, String.valueOf(nextInvoiceNumber));
  }

  public ConfigOption getConfigurationValue(ConfigKey key) {
    ConfigOption option =
            configOptionRepository
                    .findByKey(key)
                    .orElseThrow(
                            () -> new RuntimeException("Option not found with key " + key.name()));

    return option;
  }

  public void setConfigurationValues(Map<ConfigKey, String> configOptions) {
    if (configOptions.containsKey(ConfigKey.NEXT_INVOICE_NUMBER)) {
      updateNextInvoiceNumber(Integer.parseInt(configOptions.get(ConfigKey.NEXT_INVOICE_NUMBER)));
    }
  }

  public Map<ConfigKey, String> getAllConfigurations() {
    Map<ConfigKey, String> options = new HashMap<>();

    List<ConfigOption> rawOptions = configOptionRepository.findAll();

    for (ConfigOption option : rawOptions) {
      options.put(option.getKey(), option.getValue());
    }

    return options;
  }
}
