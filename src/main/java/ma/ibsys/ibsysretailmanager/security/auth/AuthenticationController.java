package ma.ibsys.ibsysretailmanager.security.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin
@Tag(
    name = "Authentication",
    description = "The Authentication API. Contains authentication end-point.")
public class AuthenticationController {

  private final AuthenticationService service;

  @Operation(
      summary = "Authenticate user",
      description = "Allows users to authenticate and retrieve their JWT token.")
  @PostMapping()
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request) {
    return ResponseEntity.ok(service.authenticate(request));
  }
}
