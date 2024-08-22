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
@RequestMapping("/cart")
public class CartController {
    private final CreateCart createCart;
    private final CartMapper cartMapper;
    private final ListProducts listProducts;
    private final GetCartState getCartState;
    private final CartProductMapper cartProductMapper;
    private final FinalizeCart finalizeCart;

    @PostMapping("/{userId}")
    public ResponseEntity<CartResponseDTO> createCart(@PathVariable String userId, @ Valid @RequestBody CartRequestDTO cartDTO)
            throws UserNotExistsException, ProductNotExistsException, OpenCartException, StockNotAvailableException {
        CartResponseDTO response = cartMapper.cartStoredBoToCartResponseDTO(createCart.run(userId, cartMapper.toNewCartBo(cartDTO)));
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{cartId}/user/{userId}")
    public ResponseEntity<CartSummaryDTO> getCartState(@PathVariable Integer cartId, @PathVariable String userId) throws UserNotExistsException {
        CartSummaryBo bo= getCartState.run(cartId,userId);
        CartSummaryDTO response = cartProductMapper.cartSummaryBoToCartSummaryDTO(bo);
        return ResponseEntity.status(200).body(response);
    }

    @PutMapping("/{cartId}/user/{userId}/checkout")
    public ResponseEntity<String> finalizeCart(@PathVariable Integer cartId, @PathVariable String userId) throws UserNotExistsException {
        finalizeCart.run(cartId, userId);
        return ResponseEntity.ok("Cart successfully closed");
    }

}
