package ma.ibsys.ibsysretailmanager.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import ma.ibsys.ibsysretailmanager.exceptions.BadRequestException;
import ma.ibsys.ibsysretailmanager.invoice.Invoice;
import ma.ibsys.ibsysretailmanager.invoice.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

    if (configOptionRepository.count() == 0) {
      List<ConfigOption> defaultConfigurations = new ArrayList<>();
      ConfigOption lastInvoice =
          ConfigOption.builder().key(ConfigKey.LAST_INVOICE_NUMBER).value("0").build();
      ConfigOption lastBL = ConfigOption.builder().key(ConfigKey.LAST_BL_NUMBER).value("0").build();

      defaultConfigurations.add(lastInvoice);
      defaultConfigurations.add(lastBL);

      AppConfiguration.configOptionRepository.saveAll(defaultConfigurations);
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

  public ConfigOption getConfigurationValue(ConfigKey key) {
    ConfigOption option =
        configOptionRepository
            .findByKey(key)
            .orElseThrow(
                () -> new RuntimeException("Option introuvable avec la clé " + key.name()));

    return option;
  }

  @Transactional
  public void setConfigurationValues(List<ConfigOptionDto> configOptionDTOs) {
    for (ConfigOptionDto configOptionDto : configOptionDTOs) {
      ConfigOption option =
          configOptionRepository
              .findByKey(configOptionDto.getKey())
              .orElseThrow(
                  () ->
                      new RuntimeException(
                          "Option introuvable avec la clé " + configOptionDto.getKey().name()));

      if (option.getKey() == ConfigKey.LAST_INVOICE_NUMBER) {
        Optional<Invoice> firstByIdDesc = invoiceRepository.findFirstByOrderByIdDesc();
        if (firstByIdDesc.isPresent()) {
          if (Integer.valueOf(configOptionDto.getValue()) < firstByIdDesc.get().getId()) {
            System.out.println(configOptionDto);
            throw new BadRequestException(
                "Le dernier numéro de facture doit être supérieur ou égal à "
                    + firstByIdDesc.get().getId());
          }
        }
      }

      option.setValue(configOptionDto.getValue());
      configOptionRepository.save(option);
    }
  }

  public List<ConfigOption> getAllConfigurations() {
    return configOptionRepository.findAll();
  }
}
