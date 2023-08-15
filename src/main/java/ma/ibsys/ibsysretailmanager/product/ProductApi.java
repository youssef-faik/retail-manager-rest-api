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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/products")
@SecurityRequirement(name = "Bearer_Authentication")
@Tag(
        name = "Product",
        description = "The Product API. Contains all operations that can be performed on a product.")
public interface ProductApi {
    @GetMapping
    @Operation(
            summary = "Get all products",
            description = "Retrieve a list containing details of all products.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of all products retrieved successfully.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    array =
                                    @ArraySchema(schema = @Schema(implementation = ProductResponseDto.class)))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request. Invalid input parameters.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized. Authentication information is missing or invalid.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error. An error occurred while processing the request.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)))
            })
    ResponseEntity<List<ProductResponseDto>> getAllProducts();

    @GetMapping("/{id}")
    @Operation(
            summary = "Get product details",
            description = "Retrieve details of the product with the given ID.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Product details retrieved successfully.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ProductResponseDto.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request. Invalid input parameters.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized. Authentication information is missing or invalid.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error. An error occurred while processing the request.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)))
            })
    ResponseEntity<ProductResponseDto> getProduct(
            @Parameter(description = "The ID of the product to retrieve.", required = true, example = "1")
            @PathVariable("id")
            int id);

    @PostMapping
    @Operation(
            summary = "Create a product",
            description = "Create a new product with the given details.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Product created successfully."),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request. Invalid input parameters.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized. Authentication information is missing or invalid.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error. An error occurred while processing the request.",
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
            summary = "Update product details",
            description = "Update details of the product with the given ID.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Product updated successfully.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ProductResponseDto.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request. Invalid input parameters.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized. Authentication information is missing or invalid.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error. An error occurred while processing the request.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)))
            })
    @PutMapping("/{id}")
    ResponseEntity<ProductResponseDto> updateProduct(
            @Parameter(description = "The ID of the product to update.", required = true, example = "1")
            @PathVariable("id")
            int id,
            @Parameter(
                    description = "The object containing the new product details to update.",
                    required = true,
                    schema = @Schema(implementation = ProductRequestDto.class))
            @RequestBody
            ProductRequestDto productRequestDto);

    @Operation(
            summary = "Delete a product",
            description = "Delete a product with the given ID.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Product deleted successfully.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request. Invalid input parameters.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized. Authentication information is missing or invalid.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error. An error occurred while processing the request.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)))
            })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteProduct(
            @Parameter(
                    name = "id",
                    description = "The ID of the product to delete.",
                    required = true,
                    example = "1")
            @PathVariable("id")
            int id);
}
