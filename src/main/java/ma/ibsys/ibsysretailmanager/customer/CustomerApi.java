package ma.ibsys.ibsysretailmanager.customer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import ma.ibsys.ibsysretailmanager.handlers.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/customers")
@SecurityRequirement(name = "Bearer_Authentication")
@Tag(
    name = "Client",
    description =
        "L'API Client. Contient toutes les opérations pouvant être effectuées sur un client.")
public interface CustomerApi {
  @GetMapping
  @Operation(
      summary = "Obtenir tous les clients.",
      description = "Obtenir une liste contenant les détails de tous les clients.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Clients récupérés avec succès.",
            content =
                @Content(
                    mediaType = "application/json",
                    array =
                        @ArraySchema(
                            schema = @Schema(implementation = CustomerResponseDto.class)))),
        @ApiResponse(
            responseCode = "401",
            description = "Non autorisé.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "403",
            description = "Accès interdit.",
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
  ResponseEntity<List<CustomerResponseDto>> getAllCustomers();

  @GetMapping("/{ice}")
  @Operation(
      summary = "Obtenir les détails du client.",
      description = "Obtenir les détails du client avec le numéro ICE donné.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Client récupéré avec succès.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CustomerResponseDto.class))),
        @ApiResponse(
            responseCode = "401",
            description = "Non autorisé.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "403",
            description = "Accès interdit.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "404",
            description = "Client non trouvé.",
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
  ResponseEntity<CustomerResponseDto> getCustomer(
      @Parameter(
              description = "Numéro ICE du client à récupérer.",
              required = true,
              example = "563456789123456")
          @PathVariable
          String ice);

  @PostMapping
  @Operation(
      summary = "Créer un client.",
      description = "Créer un nouveau client avec les détails donnés.")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "201", description = "Client créé avec succès."),
        @ApiResponse(
            responseCode = "400",
            description = "Entrée non valide.",
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
            description = "Accès interdit",
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
  ResponseEntity<Void> createCustomer(
      @Parameter(required = true, schema = @Schema(implementation = CustomerCreateDto.class))
          @Valid
          @RequestBody
          CustomerCreateDto customerCreateDto);

  @PutMapping("/{ice}")
  @Operation(
      summary = "Mettre à jour les détails du client.",
      description = "Mettre à jour les détails du client avec l'ICE donné.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Client mis à jour avec succès.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CustomerResponseDto.class))),
        @ApiResponse(
            responseCode = "400",
            description = "Entrée non valide.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "401",
            description = "Non autorisé",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "403",
            description = "Accès interdit.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "404",
            description = "Client introuvable.",
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
  ResponseEntity<CustomerResponseDto> updateCustomer(
      @Parameter(
              description = "ICE du client à mettre à jour.",
              required = true,
              example = "563456789123456")
          @PathVariable
          String ice,
      @Parameter(required = true, schema = @Schema(implementation = CustomerUpdateDto.class))
          @Valid
          @RequestBody
          CustomerUpdateDto customerUpdateDto);

  @DeleteMapping("/{ice}")
  @Operation(
      summary = "Supprimer un client.",
      description = "Supprimer un client avec l'ICE donné.")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "204", description = "Client supprimé avec succès."),
        @ApiResponse(
            responseCode = "401",
            description = "Non autorisé.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "403",
            description = "Accès interdit.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "404",
            description = "Client introuvable.",
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
  ResponseEntity<Void> deleteCustomer(
      @Parameter(
              description = "ICE du client à supprimer.",
              required = true,
              example = "563456789123456")
          @PathVariable
          String ice);
}
