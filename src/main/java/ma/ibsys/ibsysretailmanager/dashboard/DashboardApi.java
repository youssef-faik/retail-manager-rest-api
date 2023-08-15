package ma.ibsys.ibsysretailmanager.dashboard;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDate;
import ma.ibsys.ibsysretailmanager.handlers.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/v1/dashboard")
@SecurityRequirement(name = "Bearer_Authentication")
@Tag(
        name = "Dashboard",
        description = "API endpoints for dashboard metrics")
public interface DashboardApi {

    @GetMapping("/sales")
    @Operation(
            summary = "Get sales data",
            description = "Get the number of sales within a given period.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Sales data retrieved successfully",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ChartDataDto.class))),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)))
            })
    ResponseEntity<ChartDataDto> getSales(
            @Parameter(
                    description = "Start date (ISO 8601 format, e.g., 2023-05-01)",
                    example = "2023-05-01")
            @RequestParam(value = "start_date", required = false, defaultValue = "30 days ago")
            LocalDate startDate,
            @Parameter(
                    description = "End date (ISO 8601 format, e.g., 2023-05-31)",
                    example = "2023-05-31")
            @RequestParam(value = "end_date", required = false, defaultValue = "today")
            LocalDate endDate);

    @GetMapping("/orders")
    @Operation(
            summary = "Get order data",
            description = "Get the number of orders created within a given period.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Order data retrieved successfully",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ChartDataDto.class))),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)))
            })
    ResponseEntity<ChartDataDto> getOrders(
            @Parameter(
                    description = "Start date (ISO 8601 format, e.g., 2023-05-01)",
                    example = "2023-05-01")
            @RequestParam(value = "start_date", required = false, defaultValue = "30 days ago")
            LocalDate startDate,
            @Parameter(
                    description = "End date (ISO 8601 format, e.g., 2023-05-31)",
                    example = "2023-05-31")
            @RequestParam(value = "end_date", required = false, defaultValue = "today")
            LocalDate endDate);

    @GetMapping("/customers")
    @Operation(
            summary = "Get customer data",
            description = "Get the number of customers created within a given period.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Customer data retrieved successfully",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ChartDataDto.class))),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)))
            })
    ResponseEntity<ChartDataDto> getCustomers(
            @Parameter(
                    description = "Start date (ISO 8601 format, e.g., 2023-05-01)",
                    example = "2023-05-01")
            @RequestParam(value = "start_date", required = false, defaultValue = "30 days ago")
            LocalDate startDate,
            @Parameter(
                    description = "End date (ISO 8601 format, e.g., 2023-05-31)",
                    example = "2023-05-31")
            @RequestParam(value = "end_date", required = false, defaultValue = "today")
            LocalDate endDate);
}
