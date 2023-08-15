package ma.ibsys.ibsysretailmanager.invoice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import ma.ibsys.ibsysretailmanager.handlers.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/invoices")
@SecurityRequirement(name = "Bearer_Authentication")
@Tag(
        name = "Invoice",
        description = "Invoice API. Contains all operations that can be performed on an invoice.")
public interface InvoiceApi {
    @GetMapping
    @Operation(
            summary = "Get all invoices.",
            description = "Get a list containing the details of all invoices.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Invoices retrieved successfully.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = InvoiceDto.class)))),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Invoices not found.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)))
            })
    ResponseEntity<List<InvoiceDto>> getAllInvoices();

    @GetMapping("/{id}")
    @Operation(
            summary = "Get the details of an invoice.",
            description = "Get the details of the invoice with the given ID.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Invoice retrieved successfully.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = InvoiceDto.class))),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Invoice not found.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)))
            })
    ResponseEntity<InvoiceDto> getInvoice(
            @Parameter(description = "ID of the invoice to retrieve.", required = true, example = "1")
            @PathVariable
            int id);

    @GetMapping("/{id}/pdf")
    @Operation(
            summary = "Get a PDF file of an invoice.",
            description = "Retrieve a PDF file of the invoice with the provided ID.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "PDF file of the invoice generated.",
                            content = @Content(mediaType = "application/pdf")),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Invoice not found.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error.",
                            content = @Content(mediaType = "application/json"))
            })
    ResponseEntity getInvoiceReport(
            @Parameter(description = "ID of the invoice to retrieve.", required = true, example = "1")
            @PathVariable
            int id);

    @PostMapping
    @Operation(
            summary = "Create an invoice.",
            description = "Create a new invoice with the provided details.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Invoice created successfully."),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid input.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)))
            })
    ResponseEntity<Void> createInvoice(
            @Parameter(required = true, schema = @Schema(implementation = InvoiceCreateDto.class))
            @RequestBody
            InvoiceCreateDto invoiceCreateDto);
}
