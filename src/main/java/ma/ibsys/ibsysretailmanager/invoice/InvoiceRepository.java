package ma.ibsys.ibsysretailmanager.invoice;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
  List<Invoice> findByIssueDateBetween(LocalDate startDate, LocalDate endDate);

  List<Invoice> findByIssueDate(LocalDate issueDate);

  Optional<Invoice> findFirstByOrderByIdDesc();
}
