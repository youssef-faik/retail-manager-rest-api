package ma.ibsys.ibsysretailmanager.customer.repositories;

import ma.ibsys.ibsysretailmanager.customer.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {}
