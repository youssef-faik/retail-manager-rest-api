package ma.ibsys.ibsysretailmanager.security.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/auth")
@CrossOrigin
@Tag(
    name = "Authentication",
    description = "The Authentication API. Contains authentication end-point.")
public interface AuthenticationApi {
  @Operation(
      summary = "Authenticate user",
      description = "Allows users to authenticate and retrieve their JWT token.")
  @PostMapping("/login")
  ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request);
}
