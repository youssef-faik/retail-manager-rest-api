package ma.ibsys.ibsysretailmanager.invoice.repositories;

import ma.ibsys.ibsysretailmanager.invoice.entities.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Integer> {}
