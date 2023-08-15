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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
@SecurityRequirement(name = "Bearer_Authentication")
@Tag(
        name = "Category",
        description = "Category API. Contains all operations that can be performed on a Category.")
public interface CategoryApi {

    @GetMapping
    @Operation(
            summary = "Get all categories.",
            description = "Get a list of all categories.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Categories retrieved successfully.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Category.class)))),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden access.",
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
    ResponseEntity<List<Category>> getAllCategories();

    @GetMapping("/{id}")
    @Operation(
            summary = "Get category details.",
            description = "Get details of a category using the given ID.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Category retrieved successfully.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Category.class))),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden access.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Category not found.",
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
    ResponseEntity<Category> getCategoryById(
            @Parameter(
                    description = "ID of the category to retrieve.",
                    required = true,
                    example = "1")
            @PathVariable
            int id);

    @PostMapping
    @Operation(
            summary = "Create a category.",
            description = "Create a new category with the given details.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Category created successfully."),
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
                            description = "Forbidden access.",
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
    ResponseEntity<Void> createCategory(
            @Parameter(required = true, schema = @Schema(implementation = CategoryCreateDto.class))
            @Valid
            @RequestBody
            CategoryCreateDto categoryCreateDto);

    @PutMapping("/{id}")
    @Operation(
            summary = "Update category details.",
            description = "Update the details of a category with the given ID.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Category updated successfully."),
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
                            description = "Forbidden access.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Category not found.",
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
    ResponseEntity<Void> updateCategory(
            @Parameter(
                    description = "ID of the category to update.",
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
            summary = "Delete a category.",
            description = "Delete a category with the given ID.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "Category deleted successfully."),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden access.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Category not found.",
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
    ResponseEntity<Void> deleteCategory(
            @Parameter(description = "ID of the category to delete.", required = true, example = "1")
            @PathVariable
            int id);
}
