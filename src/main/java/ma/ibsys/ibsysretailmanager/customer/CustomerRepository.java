package ma.ibsys.ibsysretailmanager.customer;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
  Optional<Customer> findByICE(String ice);

  Optional<Customer> findByNameLikeIgnoreCase(String name);

  int countCustomerByName(String name);
}
