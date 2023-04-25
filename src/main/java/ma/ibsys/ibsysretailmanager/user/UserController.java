package ma.ibsys.ibsysretailmanager.user;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

  private final UserService userService;

  @Override
  public ResponseEntity<List<UserDto>> getAllUsers() {
    return userService.getAllUsers();
  }

  @Override
  public ResponseEntity<UserDto> getUser(@PathVariable("id") int id) {
    return userService.getUserById(id);
  }

  @Override
  public ResponseEntity<UserDto> createUser(@RequestBody UserCreateDto userCreateDto) {
    return userService.createUser(userCreateDto);
  }

  @Override
  public ResponseEntity<UserDto> updateUser(
      @PathVariable int id, @RequestBody UserCreateDto userCreateDto) {
    return userService.updateUser(id, userCreateDto);
  }

  @Override
  public ResponseEntity<Void> deleteUser(@PathVariable int id) {
    return userService.deleteUser(id);
  }
}