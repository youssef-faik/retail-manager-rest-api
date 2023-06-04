package ma.ibsys.ibsysretailmanager.configuration;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "ConfigOption")
@Table(name = "config_option")
public class ConfigOption {
  @Id
  @Enumerated(EnumType.STRING)
  @Column(name = "option_key", updatable = false)
  private ConfigKey key;

  @Column(name = "value", nullable = false)
  private String value;
}
