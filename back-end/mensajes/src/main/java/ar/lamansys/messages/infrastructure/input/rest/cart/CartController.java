package ar.lamansys.messages.infrastructure.input.rest.cart;

import ar.lamansys.messages.application.exception.OpenCartException;
import ar.lamansys.messages.application.exception.StockNotAvailableException;
import ar.lamansys.messages.infrastructure.DTO.CartRequestDTO;
import ar.lamansys.messages.infrastructure.mapper.CartMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ar.lamansys.messages.application.cart.CreateCart;
import ar.lamansys.messages.application.exception.ProductNotExistsException;
import ar.lamansys.messages.application.exception.UserNotExistsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {
    private final CreateCart createCart;
    private final CartMapper cartMapper;

    public CartController(CreateCart createCart, CartMapper cartMapper) {
        this.createCart = createCart;
        this.cartMapper = cartMapper;
    }

    @PostMapping("/{userId}")
    public ResponseEntity createCart(@PathVariable String userId, @RequestBody CartRequestDTO cartDTO)
            throws UserNotExistsException, ProductNotExistsException, OpenCartException, StockNotAvailableException {
        createCart.run(userId, cartMapper.toNewCartBo(cartDTO));
        return ResponseEntity.ok().build();
    }


}
