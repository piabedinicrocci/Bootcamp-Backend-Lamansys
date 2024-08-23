package ar.lamansys.messages.infrastructure.input.rest.cart;

import ar.lamansys.messages.application.cart.FinalizeCart;
import ar.lamansys.messages.application.cart.GetCartState;
import ar.lamansys.messages.application.exception.OpenCartException;
import ar.lamansys.messages.application.exception.StockNotAvailableException;
import ar.lamansys.messages.application.product.ListProducts;
import ar.lamansys.messages.domain.cart.CartSummaryBo;
import ar.lamansys.messages.infrastructure.DTO.*;
import ar.lamansys.messages.infrastructure.mapper.CartMapper;
import ar.lamansys.messages.infrastructure.mapper.CartProductMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ar.lamansys.messages.application.cart.CreateCart;
import ar.lamansys.messages.application.exception.ProductNotExistsException;
import ar.lamansys.messages.application.exception.UserNotExistsException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@Tag(name="Cart", description = "Operaciones relacionadas con los carritos de compra")
@RequestMapping("/cart")
public class CartController {
    private final CreateCart createCart;
    private final CartMapper cartMapper;
    private final ListProducts listProducts;
    private final GetCartState getCartState;
    private final CartProductMapper cartProductMapper;
    private final FinalizeCart finalizeCart;

    @PostMapping("/{userId}")
    @Operation(summary = "Crear un carrito",
            description = "Este endpoint se utiliza para crear un carrito de compra con productos especificados para un usuario."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Carrito creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud: Datos inválidos o carrito ya abierto"),
            @ApiResponse(responseCode = "404", description = "Usuario o producto no encontrado"),
            @ApiResponse(responseCode = "409", description = "Stock insuficiente")
    })
    public ResponseEntity<CartResponseDTO> createCart(@Parameter(description = "ID del usuario que abre el carrito")@PathVariable String userId, @ Valid @RequestBody CartRequestDTO cartDTO)
            throws UserNotExistsException, ProductNotExistsException, OpenCartException, StockNotAvailableException {
        CartResponseDTO response = cartMapper.cartStoredBoToCartResponseDTO(createCart.run(userId, cartMapper.toNewCartBo(cartDTO)));
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{cartId}/user/{userId}")
    @Operation(summary = "Obtener estado de carrito",
            description = "Este endpoint se utiliza para ver el estado del carrito.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado del carrito obtenido exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud: Datos inválidos o carrito ya abierto"),
            @ApiResponse(responseCode = "404", description = "Usuario o producto no encontrado"),
            @ApiResponse(responseCode = "409", description = "Stock insuficiente")
    })
    public ResponseEntity<CartSummaryDTO> getCartState(@PathVariable Integer cartId, @PathVariable String userId) throws UserNotExistsException {
        CartSummaryBo bo= getCartState.run(cartId,userId);
        CartSummaryDTO response = cartProductMapper.cartSummaryBoToCartSummaryDTO(bo);
        return ResponseEntity.status(200).body(response);
    }

    @PutMapping("/{cartId}/user/{userId}/checkout")
    @Operation(summary = "Finalizar compra",
            description = "Este endpoint se utiliza para finalizar una compra.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carrito cerrado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud: Datos inválidos o carrito ya abierto"),
            @ApiResponse(responseCode = "404", description = "Usuario o producto no encontrado"),
            @ApiResponse(responseCode = "409", description = "Stock insuficiente"),
            @ApiResponse(responseCode = "409", description = "Precio desactualizadp")
    })
    public ResponseEntity<String> finalizeCart(@PathVariable Integer cartId, @PathVariable String userId) throws UserNotExistsException {
        finalizeCart.run(cartId, userId);
        return ResponseEntity.ok("Cart successfully closed");
    }

}
