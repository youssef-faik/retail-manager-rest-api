package ma.ibsys.ibsysretailmanager.dashboard;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import ma.ibsys.ibsysretailmanager.customer.Customer;
import ma.ibsys.ibsysretailmanager.customer.CustomerRepository;
import ma.ibsys.ibsysretailmanager.invoice.Invoice;
import ma.ibsys.ibsysretailmanager.invoice.InvoiceItem;
import ma.ibsys.ibsysretailmanager.invoice.InvoiceRepository;
import ma.ibsys.ibsysretailmanager.product.Product;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {
  private final CustomerRepository customerRepository;
  private final InvoiceRepository invoiceRepository;

  public ChartDataDto getSalesData(LocalDate startDate, LocalDate endDate) {
    if (startDate == null) {
      // Default to the last 30 days
      startDate = LocalDate.now().minusDays(30);
    }

    if (endDate == null) {
      // Default to today's date
      endDate = LocalDate.now();
    } else if (endDate.isAfter(LocalDate.now())) {
      // Ensure that the endDate is not greater than today's date
      endDate = LocalDate.now();
    }

    List<String> dates = new ArrayList<>();
    List<Float> data = new ArrayList<>();

    LocalDate currentDate = startDate;
    while (!currentDate.isAfter(endDate)) {
      BigDecimal totalSalesCount = retrieveSalesCountForDate(currentDate);
      dates.add(currentDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
      data.add(totalSalesCount.floatValue());

      currentDate = currentDate.plusDays(1);
    }
    ChartDataDto chartData = new ChartDataDto(dates, data);
    return chartData;
  }

  public ChartDataDto getOrdersData(LocalDate startDate, LocalDate endDate) {
    if (startDate == null) {
      // Default to the last 30 days
      startDate = LocalDate.now().minusDays(30);
    }

    if (endDate == null) {
      // Default to today's date
      endDate = LocalDate.now();
    } else if (endDate.isAfter(LocalDate.now())) {
      // Ensure that the endDate is not greater than today's date
      endDate = LocalDate.now();
    }

    // Logic to retrieve orders data for the given period
    List<Invoice> invoices = invoiceRepository.findByIssueDateBetween(startDate, endDate);

    // Extract dates and count number of invoices for each date
    Map<LocalDate, Float> orderData = new HashMap<>();
    for (Invoice invoice : invoices) {
      LocalDate issueDate = invoice.getIssueDate();
      orderData.put(issueDate, orderData.getOrDefault(issueDate, 0f) + 1);
    }

    // Prepare chart data
    List<String> dates = new ArrayList<>();
    List<Float> data = new ArrayList<>();
    LocalDate currentDate = startDate;
    while (currentDate.isBefore(endDate) || currentDate.isEqual(endDate)) {
      dates.add(currentDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
      data.add(orderData.getOrDefault(currentDate, 0f));
      currentDate = currentDate.plusDays(1);
    }

    ChartDataDto chartData = new ChartDataDto(dates, data);
    return chartData;
  }

  public ChartDataDto getCustomersData(LocalDate startDate, LocalDate endDate) {
    if (startDate == null) {
      // Default to the last 30 days
      startDate = LocalDate.now().minusDays(30);
    }

    if (endDate == null) {
      // Default to today's date
      endDate = LocalDate.now();
    } else if (endDate.isAfter(LocalDate.now())) {
      // Ensure that the endDate is not greater than today's date
      endDate = LocalDate.now();
    }

    // Retrieve customers between the start and end dates
    List<Customer> customers = customerRepository.findByCreatedAtBetween(startDate, endDate);

    // Create a map to store the count of customers per date
    Map<LocalDate, Float> customerCountByDate = new HashMap<>();

    // Iterate over the customers and count them per date
    for (Customer customer : customers) {
      LocalDate createdAt = customer.getCreatedAt();
      customerCountByDate.put(createdAt, customerCountByDate.getOrDefault(createdAt, 0f) + 1);
    }

    // Create the chart data
    List<String> dates = new ArrayList<>();
    List<Float> data = new ArrayList<>();

    // Iterate over the date range and populate the chart data
    LocalDate currentDate = startDate;
    while (currentDate.isBefore(endDate) || currentDate.isEqual(endDate)) {
      String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
      dates.add(formattedDate);
      data.add(customerCountByDate.getOrDefault(currentDate, 0f));
      currentDate = currentDate.plusDays(1);
    }

    ChartDataDto chartData = new ChartDataDto(dates, data);
    return chartData;
  }

  private BigDecimal retrieveSalesCountForDate(LocalDate date) {
    BigDecimal totalSalesCount = BigDecimal.ZERO;

    List<Invoice> invoices = invoiceRepository.findByIssueDate(date);

    for (Invoice invoice : invoices) {
      BigDecimal invoiceTotal = BigDecimal.ZERO;
      for (InvoiceItem item : invoice.getItems()) {
        Product product = item.getProduct();
        BigDecimal unitPrice = item.getUnitPrice();
        int quantity = item.getQuantity();
        float tax = 1 + product.getTaxRate().getValue() / (float) 100;

        BigDecimal multiply = unitPrice.multiply(BigDecimal.valueOf(quantity));
        BigDecimal totalPrice = multiply.multiply(BigDecimal.valueOf(tax));
        invoiceTotal = invoiceTotal.add(totalPrice);
      }
      totalSalesCount = totalSalesCount.add(invoiceTotal);
    }

    return totalSalesCount;
  }
}
