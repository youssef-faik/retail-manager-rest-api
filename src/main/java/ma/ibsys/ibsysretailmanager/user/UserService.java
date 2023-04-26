package ma.ibsys.ibsysretailmanager.user;

import jakarta.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final ModelMapper modelMapper;

  public ResponseEntity<List<UserDto>> getAllUsers() {
    List<User> users = userRepository.findAll();
    return ResponseEntity.ok(users.stream().map(this::convertToDto).collect(Collectors.toList()));
  }

  public ResponseEntity<UserDto> getUserById(int id) {
    User user =
        userRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));
    return ResponseEntity.ok(modelMapper.map(user, UserDto.class));
  }

  public ResponseEntity<UserDto> createUser(UserCreateDto userCreateDto) {
    User user = modelMapper.map(userCreateDto, User.class);
    user.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
    User savedUser = userRepository.save(user);
    return ResponseEntity.created(URI.create("/api/users/" + savedUser.getId())).build();
  }

  public ResponseEntity<UserDto> updateUser(int id, UserUpdateDto userUpdateDto) {
    User user =
        userRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));
    user.setFirstName(userUpdateDto.getFirstName());
    user.setLastName(userUpdateDto.getLastName());
    user.setEmail(userUpdateDto.getEmail());
    user.setRole(userUpdateDto.getRole());
    User updatedUser = userRepository.save(user);
    return ResponseEntity.ok(modelMapper.map(updatedUser, UserDto.class));
  }

  public ResponseEntity<Void> deleteUser(int id) {
    userRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }

  private UserDto convertToDto(User user) {
    return modelMapper.map(user, UserDto.class);
  }
}
