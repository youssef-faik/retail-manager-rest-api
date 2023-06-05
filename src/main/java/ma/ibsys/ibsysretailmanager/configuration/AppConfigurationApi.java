package ma.ibsys.ibsysretailmanager.configuration;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import java.util.Map;
import ma.ibsys.ibsysretailmanager.handlers.ErrorResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@PreAuthorize(value = "hasRole('ADMIN')")
@SecurityRequirement(name = "Bearer_Authentication")
@RequestMapping("/api/v1/configurations")
@Tag(
    name = "Configuration",
    description = "API Configuration. Contient toutes les opérations de configuration de l'API")
public interface AppConfigurationApi {
  @GetMapping
  @Operation(
      summary = "Obtenir toutes les configurations.",
      description = "Obtenir une liste contenant les détails de toutes les configurations.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Configurations récupérées avec succès.",
            content =
                @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"NEXT_INVOICE_NUMBER\": \"123\" }"))),
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
            description = "Configurations non trouvées.",
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
  Map<ConfigKey, String> getAllConfigurations();

  @GetMapping("/{key}")
  @Operation(
      summary = "Obtenir un paramètre de configuration.",
      description = "Obtenir un paramètre de configuration avec la clé donnée.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Paramètre récupéré avec succès.",
            content =
                @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"NEXT_INVOICE_NUMBER\": \"123\" }"))),
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
            description = "Paramètre non trouvé.",
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
  ConfigOption getConfigurationValue(
      @Parameter(
              description = "Clé de configuration",
              example = "NEXT_INVOICE_NUMBER",
              schema = @Schema(implementation = ConfigKey.class))
          @PathVariable("key")
          ConfigKey key);

  @PutMapping
  @Operation(
      summary = "Définir une ou plusieurs valeurs de configuration",
      description = "Définir les valeurs d'une ou plusieurs configurations")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "204",
            description = "Valeurs de configuration définies avec succès"),
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
  void setConfigurationValues(
      @Parameter(required = true) @RequestBody @NotEmpty Map<ConfigKey, String> configOptions);
}
