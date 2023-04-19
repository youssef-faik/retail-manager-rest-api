package ma.ibsys.ibsysretailmanager.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@Tag(
    name = "User",
    description =
        "The User API. Contains all the operations that can be performed on a user. Requires ADMIN role.")
@SecurityRequirement(name = "Bearer Authentication")
@PreAuthorize(value = "hasRole('ADMIN')")
@RequestMapping("/api/v1/users")
public class UserController {

  private final UserService userService;

  @Operation(
      summary = "Get all users",
      description = "Get a list contains the details for all users.")
  @GetMapping
  public List<UserDto> getAllUsers() {
    return userService.getAllUsers();
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get user details", description = "Get the details of the given product id.")
  public ResponseEntity<UserDto> getUser(@PathVariable("id") int id) {
    UserDto user = userService.getUserById(id);
    return ResponseEntity.ok(user);
  }

  @PostMapping
  @Operation(summary = "Create user", description = "Create a new user with the given details.")
  public UserDto createUser(@RequestBody UserCreateDto userCreateDto) {
    return userService.createUser(userCreateDto);
  }

  @PutMapping("/{id}")
  @Operation(
      summary = "Update user details",
      description = "Update the details of the user with the given id.")
  public UserDto updateUser(@PathVariable int id, @RequestBody UserCreateDto userCreateDto) {
    return userService.updateUser(id, userCreateDto);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete user", description = "Delete the user with the given id.")
  public void deleteUser(@PathVariable int id) {
    userService.deleteUser(id);
  }
}
