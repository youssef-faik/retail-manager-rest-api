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
        description = "API Configuration. Contains all configuration operations.")
public interface AppConfigurationApi {
    @GetMapping
    @Operation(
            summary = "Get all configurations.",
            description = "Get a list containing details of all configurations.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Configurations retrieved successfully.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = "{ \"NEXT_INVOICE_NUMBER\": \"123\" }"))),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Configurations not found.",
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
    Map<ConfigKey, String> getAllConfigurations();

    @GetMapping("/{key}")
    @Operation(
            summary = "Get a configuration parameter.",
            description = "Get a configuration parameter with the given key.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Parameter retrieved successfully.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = "{ \"NEXT_INVOICE_NUMBER\": \"123\" }"))),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Parameter not found.",
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
    ConfigOption getConfigurationValue(
            @Parameter(
                    description = "Configuration key",
                    example = "NEXT_INVOICE_NUMBER",
                    schema = @Schema(implementation = ConfigKey.class))
            @PathVariable("key")
            ConfigKey key);

    @PutMapping
    @Operation(
            summary = "Set one or more configuration values",
            description = "Set the values of one or more configurations.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Configuration values set successfully."),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden.",
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
    void setConfigurationValues(
            @Parameter(required = true) @RequestBody @NotEmpty Map<ConfigKey, String> configOptions);
}
