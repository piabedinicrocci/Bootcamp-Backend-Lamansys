package ar.lamansys.messages.infrastructure.input.rest.cartProduct;

import ar.lamansys.messages.application.cartProduct.UpdateQuantity;
import ar.lamansys.messages.infrastructure.DTO.QuantityDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cart")
public class CartProductController {
    private final UpdateQuantity updateQuantity;

    @PutMapping("/{cartId}/product/{productId}/user/{userId}")
    public ResponseEntity<String> updateQuantity(@PathVariable String userId, @PathVariable Integer cartId, @PathVariable Integer productId, @Valid @RequestBody QuantityDTO quantity){
        updateQuantity.run(cartId, userId, productId, quantity.getQuantity());
        return ResponseEntity.ok("The quantity of the product was succesfully changed");
    }


}
