package ma.ibsys.ibsysretailmanager.security.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Authentication", description = "The Authentication API. Contains end-points for authentication and registration operations.")
public class AuthenticationController {
  
  private final AuthenticationService service;
  
  @PreAuthorize(value = "hasRole('ADMIN')")
  @Operation(summary = "Register user", description = "Allows ADMIN to register a new user.")
  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
          @RequestBody RegisterRequest request
  ) {
    return ResponseEntity.ok(service.register(request));
  }
  
  @Operation(summary = "Authenticate user", description = "Allows users to authenticate to retrieve their JWT token.")
  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
          @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }
}
