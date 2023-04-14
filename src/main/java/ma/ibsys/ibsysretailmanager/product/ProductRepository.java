package ma.ibsys.ibsysretailmanager.product.repositories;

import ma.ibsys.ibsysretailmanager.product.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {}
