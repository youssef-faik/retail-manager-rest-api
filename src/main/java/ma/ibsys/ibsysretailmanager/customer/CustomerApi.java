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
        name = "Customer",
        description = "The Client API. Contains all operations that can be performed on a client.")
public interface CustomerApi {
    @GetMapping
    @Operation(
            summary = "Get all clients.",
            description = "Get a list containing details of all clients.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Clients retrieved successfully.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    array =
                                    @ArraySchema(
                                            schema = @Schema(implementation = CustomerResponseDto.class)))),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Access forbidden.",
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
    ResponseEntity<List<CustomerResponseDto>> getAllCustomers();

    @GetMapping("/{ice}")
    @Operation(
            summary = "Get client details.",
            description = "Get details of the client with the given ICE number.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Client retrieved successfully.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CustomerResponseDto.class))),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Access forbidden.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Client not found.",
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
    ResponseEntity<CustomerResponseDto> getCustomer(
            @Parameter(
                    description = "ICE number of the client to retrieve.",
                    required = true,
                    example = "563456789123456")
            @PathVariable
            String ice);

    @PostMapping
    @Operation(
            summary = "Create a client.",
            description = "Create a new client with the given details.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Client created successfully."),
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
                            description = "Access forbidden.",
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
    ResponseEntity<Void> createCustomer(
            @Parameter(required = true, schema = @Schema(implementation = CustomerCreateDto.class))
            @Valid
            @RequestBody
            CustomerCreateDto customerCreateDto);

    @PutMapping("/{ice}")
    @Operation(
            summary = "Update client details.",
            description = "Update details of the client with the given ICE number.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Client updated successfully.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CustomerResponseDto.class))),
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
                            description = "Access forbidden.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Client not found.",
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
    ResponseEntity<CustomerResponseDto> updateCustomer(
            @Parameter(
                    description = "ICE of the client to update.",
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
            summary = "Delete a client.",
            description = "Delete a client with the given ICE number.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "Client deleted successfully."),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Access forbidden.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Client not found.",
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
    ResponseEntity<Void> deleteCustomer(
            @Parameter(
                    description = "ICE of the client to delete.",
                    required = true,
                    example = "563456789123456")
            @PathVariable
            String ice);
}
