package ma.ibsys.ibsysretailmanager.configuration;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigOptionRepository extends JpaRepository<ConfigOption, Integer> {
  Optional<ConfigOption> findByKey(ConfigKey key);
}
