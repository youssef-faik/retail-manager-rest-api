package ma.ibsys.ibsysretailmanager.invoice.repositories;

import ma.ibsys.ibsysretailmanager.invoice.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {}
