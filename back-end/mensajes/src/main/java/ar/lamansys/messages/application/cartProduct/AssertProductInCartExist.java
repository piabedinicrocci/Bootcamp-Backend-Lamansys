package ar.lamansys.messages.application.cartProduct;

import ar.lamansys.messages.application.cartProduct.port.CartProductStorage;
import ar.lamansys.messages.application.exception.ProductNotInCartException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AssertProductInCartExist {
    private final CartProductStorage cartProductStorage;

    public void run(Integer cartId, Integer productId) throws ProductNotInCartException{
        if(cartProductStorage.findByCartIdAndProductId(cartId, productId) == null){
            throw new ProductNotInCartException(cartId, productId);
        }

    }
}
