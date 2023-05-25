package ma.ibsys.ibsysretailmanager.product;

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

@RequestMapping("/api/v1/products")
@SecurityRequirement(name = "Bearer_Authentication")
@Tag(
    name = "Produit",
    description =
        "L'API Produit. Contient toutes les opérations pouvant être effectuées sur un produit")
public interface ProductApi {
  @GetMapping
  @Operation(
      summary = "Obtenir tous les produits",
      description = "Obtenir une liste contenant les détails de tous les produits.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Liste de tous les produits récupérée avec succès.",
            content =
                @Content(
                    mediaType = "application/json",
                    array =
                        @ArraySchema(schema = @Schema(implementation = ProductResponseDto.class)))),
        @ApiResponse(
            responseCode = "400",
            description = "Requête incorrecte. Paramètres d'entrée non valides.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "401",
            description =
                "Non autorisé. Les informations d'authentification sont manquantes ou invalides.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "500",
            description =
                "Erreur interne du serveur. Une erreur s'est produite lors du traitement de la requête.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
      })
  ResponseEntity<List<ProductResponseDto>> getAllProducts();

  @GetMapping("/{id}")
  @Operation(
      summary = "Obtenir les détails du produit",
      description = "Obtenir les détails du produit avec l'ID donné.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Détails du produit",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ProductResponseDto.class))),
        @ApiResponse(
            responseCode = "400",
            description = "Requête incorrecte. Paramètres d'entrée non valides.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "401",
            description =
                "Non autorisé. Les informations d'authentification sont manquantes ou invalides.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "500",
            description =
                "Erreur interne du serveur. Une erreur s'est produite lors du traitement de la requête.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
      })
  ResponseEntity<ProductResponseDto> getProduct(
      @Parameter(description = "L'ID du produit à récupérer.", required = true, example = "1")
          @PathVariable("id")
          int id);

  @PostMapping
  @Operation(
      summary = "Créer un produit.",
      description = "Créer un nouveau produit avec les détails donnés.")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "201", description = "Produit créé avec succès."),
        @ApiResponse(
            responseCode = "400",
            description = "Requête incorrecte. Paramètres d'entrée non valides.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "401",
            description =
                "Non autorisé. Les informations d'authentification sont manquantes ou invalides.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "500",
            description =
                "Erreur interne du serveur. Une erreur s'est produite lors du traitement de la requête.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
      })
  ResponseEntity<Void> createProduct(
      @Parameter(required = true, schema = @Schema(implementation = ProductRequestDto.class))
          @RequestBody
          ProductRequestDto productRequestDto);

  @Operation(
      summary = "Mettre à jour les détails du produit.",
      description = "Mettre à jour les détails du produit avec l'ID donné.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Produit mis à jour avec succès.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ProductResponseDto.class))),
        @ApiResponse(
            responseCode = "400",
            description = "Requête incorrecte. Paramètres d'entrée non valides.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "401",
            description =
                "Non autorisé. Les informations d'authentification sont manquantes ou invalides.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "500",
            description =
                "Erreur interne du serveur. Une erreur s'est produite lors du traitement de la requête.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
      })
  @PutMapping("/{id}")
  ResponseEntity<ProductResponseDto> updateProduct(
      @Parameter(description = "L'ID du produit à mettre à jour.", required = true, example = "1")
          @PathVariable("id")
          int id,
      @Parameter(
              description = "L'objet contenant les nouveaux détails du produit à mettre à jour.",
              required = true,
              schema = @Schema(implementation = ProductRequestDto.class))
          @RequestBody
          ProductRequestDto productRequestDto);

  @Operation(
      summary = "Supprimer un produit",
      description = "Supprimer un produit avec l'ID donné.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "204",
            description = "Produit supprimé avec succès.",
            content = @Content(mediaType = "application/json")),
        @ApiResponse(
            responseCode = "400",
            description = "Requête incorrecte. Paramètres d'entrée non valides.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "401",
            description =
                "Non autorisé. Les informations d'authentification sont manquantes ou invalides.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "500",
            description =
                "Erreur interne du serveur. Une erreur s'est produite lors du traitement de la requête.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
      })
  @DeleteMapping("/{id}")
  ResponseEntity<Void> deleteProduct(
      @Parameter(
              name = "id",
              description = "L'ID du produit à supprimer.",
              required = true,
              example = "1")
          @PathVariable("id")
          int id);
}
