package ma.ibsys.ibsysretailmanager.user;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final ModelMapper modelMapper;

  public List<UserDto> getAllUsers() {
    List<User> users = userRepository.findAll();
    return users.stream().map(this::convertToDto).collect(Collectors.toList());
  }

  public UserDto createUser(UserCreateDto userCreateDto) {
    User user = modelMapper.map(userCreateDto, User.class);
    user.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
    User savedUser = userRepository.save(user);
    return modelMapper.map(savedUser, UserDto.class);
  }

  public UserDto updateUser(int id, UserCreateDto userCreateDto) {
    User user = modelMapper.map(userCreateDto, User.class);
    user.setId(id);
    user.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
    User updatedUser = userRepository.save(user);
    return modelMapper.map(updatedUser, UserDto.class);
  }

  public void deleteUser(int id) {
    userRepository.deleteById(id);
  }

  private UserDto convertToDto(User user) {
    return modelMapper.map(user, UserDto.class);
  }

  public UserDto getUserById(int id) {
    User user =
        userRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));
    return modelMapper.map(user, UserDto.class);
  }
}
