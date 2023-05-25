package ma.ibsys.ibsysretailmanager.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import ma.ibsys.ibsysretailmanager.handlers.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(
    name = "Utilisateur",
    description =
        "API Utilisateur. Contient toutes les opérations pouvant être effectuées sur un utilisateur. Nécessite le rôle ADMIN.")
@SecurityRequirement(name = "Bearer_Authentication")
@RequestMapping("/api/v1/users")
public interface UserApi {
  @PreAuthorize(value = "hasRole('ADMIN')")
  @Operation(
      summary = "Obtenir tous les utilisateurs.",
      description = "Obtenir une liste contenant les détails de tous les utilisateurs.")
  @GetMapping
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Utilisateurs récupérés avec succès.",
            content =
                @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
        @ApiResponse(
            responseCode = "401",
            description = "Non autorisé.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "403",
            description = "Interdit.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "404",
            description = "Utilisateurs introuvables.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "500",
            description = "Erreur interne du serveur.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
      })
  ResponseEntity<List<UserDto>> getAllUsers();

  @PreAuthorize(value = "hasRole('ADMIN')")
  @GetMapping("/{id}")
  @Operation(
      summary = "Obtenir les détails de l'utilisateur.",
      description = "Obtenir les détails de l'utilisateur avec l'ID donné.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Utilisateur récupéré avec succès.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = UserDto.class))),
        @ApiResponse(
            responseCode = "401",
            description = "Non autorisé.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "403",
            description = "Interdit.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "404",
            description = "Utilisateur introuvable.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "500",
            description = "Erreur interne du serveur.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
      })
  ResponseEntity<UserDto> getUser(
      @Parameter(description = "ID de l'utilisateur à récupérer.", required = true, example = "1")
          @PathVariable("id")
          int id);

  @PreAuthorize(value = "hasRole('ADMIN')")
  @PostMapping
  @Operation(
      summary = "Créer un utilisateur.",
      description = "Créer un nouvel utilisateur avec les détails donnés.")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "201", description = "Utilisateur créé avec succès."),
        @ApiResponse(
            responseCode = "400",
            description = "Entrée non valide.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "401",
            description = "Non autorisé.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "403",
            description = "Interdit.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "500",
            description = "Erreur interne du serveur.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
      })
  ResponseEntity<Void> createUser(
      @Parameter(required = true, schema = @Schema(implementation = UserCreateDto.class))
          @RequestBody
          UserCreateDto userCreateDto);

  @PreAuthorize(value = "hasRole('ADMIN')")
  @PutMapping("/{id}")
  @Operation(
      summary = "Mettre à jour les détails de l'utilisateur.",
      description = "Mettre à jour les détails de l'utilisateur avec l'ID donné.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Utilisateur mis à jour avec succès.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = UserDto.class))),
        @ApiResponse(
            responseCode = "400",
            description = "Entrée non valide.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "401",
            description = "Non autorisé.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "403",
            description = "Interdit.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "404",
            description = "Utilisateur introuvable.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "500",
            description = "Erreur interne du serveur.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
      })
  ResponseEntity<UserDto> updateUser(
      @Parameter(
              description = "ID de l'utilisateur à mettre à jour.",
              required = true,
              example = "123")
          @PathVariable
          int id,
      @Parameter(required = true, schema = @Schema(implementation = UserUpdateDto.class))
          @RequestBody
          UserUpdateDto userUpdateDto);

  @PreAuthorize(value = "hasRole('ADMIN')")
  @DeleteMapping("/{id}")
  @Operation(
      summary = "Supprimer un utilisateur.",
      description = "Supprimer l'utilisateur avec l'ID donné.")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "204", description = "Utilisateur supprimé avec succès."),
        @ApiResponse(
            responseCode = "401",
            description = "Non autorisé.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "403",
            description = "Interdit",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "404",
            description = "Utilisateur introuvable.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "500",
            description = "Erreur interne du serveur.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
      })
  ResponseEntity<Void> deleteUser(
      @Parameter(description = "ID de l'utilisateur à supprimer.", required = true, example = "1")
          @PathVariable
          int id);

  @PutMapping("/change-password")
  @Operation(
      summary = "Modifier le mot de passe de l'utilisateur.",
      description = "Met à jour le mot de passe de l'utilisateur avec un nouveau.")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Mot de passe modifié avec succès."),
        @ApiResponse(
            responseCode = "400",
            description = "Requête ou paramètres non valides.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "401",
            description = "Accès non autorisé",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "500",
            description = "Erreur interne du serveur.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
      })
  ResponseEntity<Void> changePassword(
      @Parameter(required = true, schema = @Schema(implementation = ChangePasswordRequest.class))
          @RequestBody
          ChangePasswordRequest changePasswordRequest);
}
