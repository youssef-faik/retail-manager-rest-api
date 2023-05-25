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
    name = "Tableau de bord",
    description = "Points d'accès API pour les métriques du tableau de bord")
public interface DashboardApi {

  @GetMapping("/sales")
  @Operation(
      summary = "Obtenir les données de ventes",
      description = "Obtenir le nombre de ventes sur une période donnée.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Données de ventes récupérées avec succès",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ChartDataDto.class))),
        @ApiResponse(
            responseCode = "401",
            description = "Non autorisé",
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
            description = "Erreur interne du serveur",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
      })
  ResponseEntity<ChartDataDto> getSales(
      @Parameter(
              description = "Date de début (format ISO 8601, par ex. : 2023-05-01)",
              example = "2023-05-01")
          @RequestParam(value = "start_date", required = false, defaultValue = "il y a 30 jours")
          LocalDate startDate,
      @Parameter(
              description = "Date de fin (format ISO 8601, par ex. : 2023-05-31)",
              example = "2023-05-31")
          @RequestParam(value = "end_date", required = false, defaultValue = "aujourd'hui")
          LocalDate endDate);

  @GetMapping("/orders")
  @Operation(
      summary = "Obtenir les données de commandes",
      description = "Obtenir le nombre de commandes créées sur une période donnée.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Données de commandes récupérées avec succès",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ChartDataDto.class))),
        @ApiResponse(
            responseCode = "401",
            description = "Non autorisé",
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
            description = "Erreur interne du serveur",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
      })
  ResponseEntity<ChartDataDto> getOrders(
      @Parameter(
              description = "Date de début (format ISO 8601, par ex. : 2023-05-01)",
              example = "2023-05-01")
          @RequestParam(value = "start_date", required = false, defaultValue = "il y a 30 jours")
          LocalDate startDate,
      @Parameter(
              description = "Date de fin (format ISO 8601, par ex. : 2023-05-31)",
              example = "2023-05-31")
          @RequestParam(value = "end_date", required = false, defaultValue = "aujourd'hui")
          LocalDate endDate);

  @GetMapping("/customers")
  @Operation(
      summary = "Obtenir les données des clients",
      description = "Obtenir le nombre de clients créés sur une période donnée.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Données des clients récupérées avec succès",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ChartDataDto.class))),
        @ApiResponse(
            responseCode = "401",
            description = "Non autorisé",
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
            description = "Erreur interne du serveur",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
      })
  ResponseEntity<ChartDataDto> getCustomers(
      @Parameter(
              description = "Date de début (format ISO 8601, par ex. : 2023-05-01)",
              example = "2023-05-01")
          @RequestParam(value = "start_date", required = false, defaultValue = "il y a 30 jours")
          LocalDate startDate,
      @Parameter(
              description = "Date de fin (format ISO 8601, par ex. : 2023-05-31)",
              example = "2023-05-31")
          @RequestParam(value = "end_date", required = false, defaultValue = "aujourd'hui")
          LocalDate endDate);
}
