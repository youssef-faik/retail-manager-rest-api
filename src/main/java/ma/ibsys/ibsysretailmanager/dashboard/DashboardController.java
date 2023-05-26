package ma.ibsys.ibsysretailmanager.dashboard;

import java.time.LocalDate;
import java.util.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DashboardController implements DashboardApi {
  private final DashboardService dashboardService;

  @Override
  public ResponseEntity<ChartDataDto> getSales(
      @RequestParam(value = "startDate", required = false) LocalDate startDate,
      @RequestParam(value = "endDate", required = false) LocalDate endDate) {

    return ResponseEntity.ok(dashboardService.getSalesData(startDate, endDate));
  }

  @Override
  public ResponseEntity<ChartDataDto> getOrders(
      @RequestParam(value = "startDate", required = false) LocalDate startDate,
      @RequestParam(value = "endDate", required = false) LocalDate endDate) {

    return ResponseEntity.ok(dashboardService.getOrdersData(startDate, endDate));
  }

  @Override
  public ResponseEntity<ChartDataDto> getCustomers(
      @RequestParam(value = "startDate", required = false) LocalDate startDate,
      @RequestParam(value = "endDate", required = false) LocalDate endDate) {

    return ResponseEntity.ok(dashboardService.getCustomersData(startDate, endDate));
  }
}
