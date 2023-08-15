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
@Tag(name = "Cart Item", description = "API to manage cart items.")
public interface CartItemApi {

    @GetMapping(value = "/{id}")
    @Operation(
            summary = "Get a cart item by ID.",
            description = "Retrieve a cart item using its ID.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Cart item found.",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CartItem.class))),
                    @ApiResponse(responseCode = "404", description = "Cart item not found.")
            })
    ResponseEntity<CartItem> getCartItemById(
            @Parameter(
                    description = "ID of the cart item.",
                    required = true,
                    example = "563456789123456")
            @PathVariable
            Long id);

    @PostMapping()
    @Operation(
            summary = "Save a cart item.",
            description = "Save a new cart item.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cart item saved."),
                    @ApiResponse(responseCode = "404", description = "Product not found.")
            })
    ResponseEntity<Void> saveCartItem(
            @Parameter(required = true, schema = @Schema(implementation = CartItemRequestDto.class))
            @Valid
            @RequestBody
            CartItemRequestDto cartItemRequest);
}
