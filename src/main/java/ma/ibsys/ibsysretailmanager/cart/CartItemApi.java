package ma.ibsys.ibsysretailmanager.cart;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cart-item")
@SecurityRequirement(name = "Bearer_Authentication")
@Tag(name = "Element Panier", description = "API pour gérer les éléments du panier.")
public interface CartItemApi {

  @GetMapping(value = "/{id}")
  @Operation(
      summary = "Obtenir un élément du panier par ID.",
      description = "Récupère un élément du panier en utilisant son ID.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Élément du panier trouvé.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CartItem.class))),
        @ApiResponse(responseCode = "404", description = "Élément du panier non trouvé.")
      })
  ResponseEntity<CartItem> geCartItemById(
      @Parameter(
              description = "ID de l'élément du panier.",
              required = true,
              example = "563456789123456")
          @PathVariable
          Long id);

  @PostMapping()
  @Operation(
      summary = "Enregistrer un élément du panier.",
      description = "Enregistre un nouvel élément du panier.")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Élément du panier enregistré."),
        @ApiResponse(responseCode = "404", description = "Produit non trouvé.")
      })
  ResponseEntity<Void> saveCartItem(
      @Parameter(required = true, schema = @Schema(implementation = CartItemRequestDto.class))
          @Valid
          @RequestBody
          CartItemRequestDto cartItemRequest);
}
