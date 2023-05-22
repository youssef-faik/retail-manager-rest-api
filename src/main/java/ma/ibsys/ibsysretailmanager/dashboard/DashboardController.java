package ma.ibsys.ibsysretailmanager.dashboard;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DashboardController implements DashboardApi {

  @Override
  public ResponseEntity<ChartDataDto> getSales(
      @RequestParam(value = "startDate", required = false) LocalDate startDate,
      @RequestParam(value = "endDate", required = false) LocalDate endDate) {
    // Logic to retrieve sales data for the given period
    // Replace this with your actual implementation

    // Example data
    List<String> dates =
        Arrays.asList(
            "Jan 01 2022",
            "Jan 02 2022",
            "Jan 03 2022",
            "Jan 04 2022",
            "Jan 05 2022",
            "Jan 06 2022",
            "Jan 07 2022",
            "Jan 08 2022",
            "Jan 09 2022",
            "Jan 10 2022",
            "Jan 11 2022");
    List<Integer> data = Arrays.asList(36, 77, 0, 90, 74, 35, 0, 23, 47, 0, 63);

    ChartDataDto chartData = new ChartDataDto(dates, data);
    return ResponseEntity.ok(chartData);
  }

  @Override
  public ResponseEntity<ChartDataDto> getRevenue(
      @RequestParam(value = "startDate", required = false) LocalDate startDate,
      @RequestParam(value = "endDate", required = false) LocalDate endDate) {
    // Logic to retrieve revenue data for the given period
    // Replace this with your actual implementation

    // Example data
    List<String> dates =
        Arrays.asList(
            "Jan 01 2022",
            "Jan 02 2022",
            "Jan 03 2022",
            "Jan 04 2022",
            "Jan 05 2022",
            "Jan 06 2022",
            "Jan 07 2022",
            "Jan 08 2022",
            "Jan 09 2022",
            "Jan 10 2022",
            "Jan 11 2022");
    List<Integer> data = Arrays.asList(500, 750, 0, 600, 800, 700, 0, 400, 650, 850, 950);

    ChartDataDto chartData = new ChartDataDto(dates, data);
    return ResponseEntity.ok(chartData);
  }

  @Override
  public ResponseEntity<ChartDataDto> getOrders(
      @RequestParam(value = "startDate", required = false) LocalDate startDate,
      @RequestParam(value = "endDate", required = false) LocalDate endDate) {
    // Logic to retrieve orders data for the given period
    // Replace this with your actual implementation

    // Example data
    List<String> dates =
        Arrays.asList(
            "Jan 01 2022",
            "Jan 02 2022",
            "Jan 03 2022",
            "Jan 04 2022",
            "Jan 05 2022",
            "Jan 06 2022",
            "Jan 07 2022",
            "Jan 08 2022",
            "Jan 09 2022",
            "Jan 10 2022",
            "Jan 11 2022");
    List<Integer> data = Arrays.asList(10, 20, 0, 1, 18, 0, 30, 22, 0, 23, 28);

    ChartDataDto chartData = new ChartDataDto(dates, data);
    return ResponseEntity.ok(chartData);
  }

  @Override
  public ResponseEntity<ChartDataDto> getCustomers(
      @RequestParam(value = "startDate", required = false) LocalDate startDate,
      @RequestParam(value = "endDate", required = false) LocalDate endDate) {
    // Logic to retrieve customers data for the given period
    // Replace this with your actual implementation

    // Example data
    List<String> dates =
        Arrays.asList(
            "Jan 01 2022",
            "Jan 02 2022",
            "Jan 03 2022",
            "Jan 04 2022",
            "Jan 05 2022",
            "Jan 06 2022",
            "Jan 07 2022",
            "Jan 08 2022",
            "Jan 09 2022",
            "Jan 10 2022",
            "Jan 11 2022");
    List<Integer> data = Arrays.asList(21, 14, 10, 3, 14, 17, 11, 5, 1, 23, 28);

    ChartDataDto chartData = new ChartDataDto(dates, data);
    return ResponseEntity.ok(chartData);
  }
}
