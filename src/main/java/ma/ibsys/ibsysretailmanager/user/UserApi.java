package ma.ibsys.ibsysretailmanager.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(
    name = "User",
    description =
        "The User API. Contains all the operations that can be performed on a user. Requires ADMIN role.")
@CrossOrigin
@SecurityRequirement(name = "Bearer Authentication")
@PreAuthorize(value = "hasRole('ADMIN')")
@RequestMapping("/api/v1/users")
public interface UserApi {
  @Operation(
      summary = "Get all users",
      description = "Get a list contains the details for all users.")
  @GetMapping
  ResponseEntity<List<UserDto>> getAllUsers();

  @GetMapping("/{id}")
  @Operation(summary = "Get user details", description = "Get the details of the given product id.")
  ResponseEntity<UserDto> getUser(@PathVariable("id") int id);

  @PostMapping
  @Operation(summary = "Create user", description = "Create a new user with the given details.")
  ResponseEntity<UserDto> createUser(@RequestBody UserCreateDto userCreateDto);

  @PutMapping("/{id}")
  @Operation(
      summary = "Update user details",
      description = "Update the details of the user with the given id.")
  ResponseEntity<UserDto> updateUser(
      @PathVariable int id, @RequestBody UserUpdateDto userUpdateDto);

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete user", description = "Delete the user with the given id.")
  ResponseEntity<Void> deleteUser(@PathVariable int id);
}
