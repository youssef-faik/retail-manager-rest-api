package ma.ibsys.ibsysretailmanager.security.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthenticationController implements AuthenticationApi {

  private final AuthenticationService service;

  @Override
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request) {
    return service.authenticate(request);
  }
}
