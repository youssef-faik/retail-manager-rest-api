package ma.ibsys.ibsysretailmanager.category;

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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
@SecurityRequirement(name = "Bearer_Authentication")
@Tag(
    name = "Categorie",
    description =
        "L'API Catégorie. Contient toutes les opérations pouvant être effectuées sur une Catégorie.")
public interface CategoryApi {

  @GetMapping
  @Operation(
      summary = "Obtenir toutes les catégories.",
      description = "Obtenir une liste de toutes les catégories.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Catégories récupérées avec succès.",
            content =
                @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Category.class)))),
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
  ResponseEntity<List<Category>> getAllCategories();

  @GetMapping("/{id}")
  @Operation(
      summary = "Obtenir les détails d'une catégorie.",
      description = "Obtenir les détails d'une catégorie avec le numéro ID donné.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Catégorie récupérée avec succès.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Category.class))),
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
            description = "Catégorie non trouvée.",
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
  ResponseEntity<Category> getCategoryById(
      @Parameter(
              description = "Numéro ID de la catégorie à récupérer.",
              required = true,
              example = "1")
          @PathVariable
          int id);

  @PostMapping
  @Operation(
      summary = "Créer une catégorie.",
      description = "Créer une nouvelle catégorie avec les détails donnés.")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "201", description = "Catégorie créée avec succès."),
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
  ResponseEntity<Void> createCategory(
      @Parameter(required = true, schema = @Schema(implementation = CategoryCreateDto.class))
          @Valid
          @RequestBody
          CategoryCreateDto categoryCreateDto);

  @PutMapping("/{id}")
  @Operation(
      summary = "Mettre à jour les détails de la catégorie.",
      description = "Mettre à jour les détails de la catégorie avec l'ID donné.")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Catégorie mise à jour avec succès."),
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
            description = "Catégorie introuvable.",
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
  ResponseEntity<Void> updateCategory(
      @Parameter(
              description = "ID de la catégorie à mettre à jour.",
              required = true,
              example = "1")
          @PathVariable
          int id,
      @Parameter(required = true, schema = @Schema(implementation = CategoryCreateDto.class))
          @Valid
          @RequestBody
          CategoryCreateDto categoryCreateDto);

  @DeleteMapping("/{id}")
  @Operation(
      summary = "Supprimer une catégorie.",
      description = "Supprimer une catégorie avec l'ID donné.")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "204", description = "Catégorie supprimée avec succès."),
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
            description = "Catégorie introuvable.",
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
  ResponseEntity<Void> deleteCategory(
      @Parameter(description = "ID de la catégorie à supprimer.", required = true, example = "1")
          @PathVariable
          int id);
}
