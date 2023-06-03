package ma.ibsys.ibsysretailmanager.category;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
    title = "Schéma de requête de création/mise a jour d'une catégorie",
    description = "Corps de requête pour créer/mise a jour d'une catégorie")
public class CategoryCreateDto {
  @NotBlank(message = "Le nom de la catégorie est requis")
  @Size(max = 100, message = "Le nom de la catégorie ne doit pas dépasser {max} caractères")
  @Schema(description = "Nom de la catégorie", example = "Électronique")
  private String name;

  @Size(max = 500, message = "La description de la catégorie ne doit pas dépasser {max} caractères")
  @Schema(
      description = "Description de la catégorie",
      example = "Catégorie pour les produits électroniques")
  private String description;
}
