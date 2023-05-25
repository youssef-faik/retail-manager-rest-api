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
    name = "Facture",
    description =
        "API Facture. Contient toutes les opérations pouvant être effectuées sur une facture.")
public interface InvoiceApi {
  @GetMapping
  @Operation(
      summary = "Obtenir toutes les factures.",
      description = "Obtenir une liste contenant les détails de toutes les factures.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Factures récupérées avec succès.",
            content =
                @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = InvoiceDto.class)))),
        @ApiResponse(
            responseCode = "401",
            description = "Non autorisé.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "403",
            description = "Interdit",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "404",
            description = "Factures non trouvées.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "500",
            description = "Erreur interne du serveur.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
      })
  ResponseEntity<List<InvoiceDto>> getAllInvoices();

  @GetMapping("/{id}")
  @Operation(
      summary = "Obtenir les détails d'une facture.",
      description = "Obtenir les détails de la facture avec l'identifiant donné.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Facture récupérée avec succès.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = InvoiceDto.class))),
        @ApiResponse(
            responseCode = "401",
            description = "Non autorisé.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "403",
            description = "Interdit.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "404",
            description = "Facture non trouvée.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "500",
            description = "Erreur interne du serveur.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
      })
  ResponseEntity<InvoiceDto> getInvoice(
      @Parameter(description = "ID de la facture à récupérer.", required = true, example = "1")
          @PathVariable
          int id);

  @PostMapping
  @Operation(
      summary = "Créer une facture.",
      description = "Créer une nouvelle facture avec les détails fournis.")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "201", description = "Facture créée avec succès."),
        @ApiResponse(
            responseCode = "400",
            description = "Entrée invalide.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "401",
            description = "Non autorisé.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "403",
            description = "Interdit",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "500",
            description = "Erreur interne du serveur.",
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
