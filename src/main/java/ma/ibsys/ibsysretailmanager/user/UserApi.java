package ma.ibsys.ibsysretailmanager.user;

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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(
        name = "User",
        description =
                "User API. Contains all operations that can be performed on a user. Requires ADMIN role.")
@SecurityRequirement(name = "Bearer_Authentication")
@RequestMapping("/api/v1/users")
public interface UserApi {
    @PreAuthorize(value = "hasRole('ADMIN')")
    @Operation(
            summary = "Get all users.",
            description = "Get a list containing details of all users.")
    @GetMapping
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Users retrieved successfully.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
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
                            description = "Users not found.",
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
    ResponseEntity<List<UserDto>> getAllUsers();

    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping("/{id}")
    @Operation(
            summary = "Get user details.",
            description = "Get details of the user with the given ID.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User retrieved successfully.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UserDto.class))),
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
                            description = "User not found.",
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
    ResponseEntity<UserDto> getUser(
            @Parameter(description = "ID of the user to retrieve.", required = true, example = "1")
            @PathVariable("id")
            int id);

    @PreAuthorize(value = "hasRole('ADMIN')")
    @PostMapping
    @Operation(
            summary = "Create a user.",
            description = "Create a new user with the given details.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "User created successfully."),
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
    ResponseEntity<Void> createUser(
            @Parameter(required = true, schema = @Schema(implementation = UserCreateDto.class))
            @RequestBody
            UserCreateDto userCreateDto);

    @PreAuthorize(value = "hasRole('ADMIN')")
    @PutMapping("/{id}")
    @Operation(
            summary = "Update user details.",
            description = "Update details of the user with the given ID.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User updated successfully.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UserDto.class))),
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
                            description = "Forbidden.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User not found.",
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
    ResponseEntity<UserDto> updateUser(
            @Parameter(
                    description = "ID of the user to update.",
                    required = true,
                    example = "123")
            @PathVariable
            int id,
            @Parameter(required = true, schema = @Schema(implementation = UserUpdateDto.class))
            @RequestBody
            UserUpdateDto userUpdateDto);

    @PreAuthorize(value = "hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a user.",
            description = "Delete the user with the given ID.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "User deleted successfully."),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User not found.",
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
    ResponseEntity<Void> deleteUser(
            @Parameter(description = "ID of the user to delete.", required = true, example = "1")
            @PathVariable
            int id);

    @PutMapping("/change-password")
    @Operation(
            summary = "Change user's password.",
            description = "Update user's password with a new one.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Password changed successfully."),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid request or parameters.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized access",
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
    ResponseEntity<Void> changePassword(
            @Parameter(required = true, schema = @Schema(implementation = ChangePasswordRequest.class))
            @RequestBody
            ChangePasswordRequest changePasswordRequest);
}
