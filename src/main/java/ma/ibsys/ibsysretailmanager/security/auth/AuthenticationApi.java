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
    name = "Authentification",
    description = "L'API d'authentification. Contient le point de terminaison d'authentification.")
public interface AuthenticationApi {
  @Operation(
      summary = "Authentifier l'utilisateur.",
      description = "Permet aux utilisateurs de s'authentifier et de récupérer leur jeton JWT.")
  @PostMapping("/login")
  ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request);
}
