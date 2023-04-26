package ma.ibsys.ibsysretailmanager.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@SecurityRequirement(name = "Bearer Authentication")
@Tag(
    name = "Product",
    description = "The Product API. Contains all the operations that can be performed on a product")
public interface ProductApi {
  @GetMapping
  @Operation(
      summary = "Get all products",
      description = "Get a list that contains the details for all products.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully Retrieved the list of all products",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ProductResponseDto.class))),
        @ApiResponse(
            responseCode = "400",
            description = "Bad Request. Invalid input parameters.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized. Authentication credentials are missing or invalid.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "500",
            description = "Internal Server Error. An error occurred while processing the request.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
      })
  ResponseEntity<List<ProductResponseDto>> getAllCustomers();

  @GetMapping("/{id}")
  @Operation(
      summary = "Get product details",
      description = "Get the details of the product with the given id.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Product details",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ProductResponseDto.class))),
        @ApiResponse(
            responseCode = "400",
            description = "Bad Request. Invalid input parameters.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized. Authentication credentials are missing or invalid.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "500",
            description = "Internal Server Error. An error occurred while processing the request.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
      })
  ResponseEntity<ProductResponseDto> getProduct(
      @Parameter(description = "The id of the product to retrieve", required = true, example = "1")
          @PathVariable("id")
          int id);

  @PostMapping
  @Operation(
      summary = "Create product",
      description = "Create a new product with the given details.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "201",
            description = "Product created successfully."),
        @ApiResponse(
            responseCode = "400",
            description = "Bad Request. Invalid input parameters.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized. Authentication credentials are missing or invalid.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "500",
            description = "Internal Server Error. An error occurred while processing the request.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
      })
  ResponseEntity<ProductResponseDto> createProduct(
      @Parameter(
              required = true,
              schema = @Schema(implementation = ProductRequestDto.class))
          @RequestBody
          ProductRequestDto productRequestDto);

  @Operation(
      summary = "Update product details",
      description = "Update the details of the product with given id.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Product updated successfully",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ProductResponseDto.class))),
        @ApiResponse(
            responseCode = "400",
            description = "Bad Request. Invalid input parameters.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized. Authentication credentials are missing or invalid.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "500",
            description = "Internal Server Error. An error occurred while processing the request.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
      })
  @PutMapping("/{id}")
  ResponseEntity<ProductResponseDto> updateProduct(
      @Parameter(description = "The id of the product to update", required = true, example = "1")
          @PathVariable("id")
          int id,
      @Parameter(
              description = "The object containing the new details of the product to update.",
              required = true,
              schema = @Schema(implementation = ProductRequestDto.class))
          @RequestBody
          ProductRequestDto productRequestDto);

  @Operation(summary = "Delete product", description = "Delete a product with given id.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "204",
            description = "Product deleted successfully.",
            content = @Content(mediaType = "application/json")),
        @ApiResponse(
            responseCode = "400",
            description = "Bad Request. Invalid input parameters.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized. Authentication credentials are missing or invalid.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "500",
            description = "Internal Server Error. An error occurred while processing the request.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
      })
  @DeleteMapping("/{id}")
  ResponseEntity<Void> deleteProduct(
      @Parameter(name = "id", description = "The id of the product to delete", required = true, example = "1")
          @PathVariable("id")
          int id);
}
