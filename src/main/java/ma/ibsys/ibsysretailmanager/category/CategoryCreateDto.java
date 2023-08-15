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
        title = "Schema for creating/updating a category",
        description = "Request body for creating/updating a category")
public class CategoryCreateDto {
  @NotBlank(message = "Category name is required.")
  @Size(max = 100, message = "Category name must not exceed {max} characters.")
  @Schema(description = "Category name", example = "Electronics")
  private String name;

  @Size(max = 500, message = "Category description must not exceed {max} characters.")
  @Schema(
          description = "Category description",
          example = "Category for electronic products")
  private String description;
}
