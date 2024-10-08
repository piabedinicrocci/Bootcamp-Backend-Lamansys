package ar.lamansys.messages.application.cart;

import ar.lamansys.messages.application.cart.port.CartStorage;
import ar.lamansys.messages.application.exception.CartUserNotExistsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AssertCartUserExist {
    private final CartStorage cartStorage;

    public void run(Integer cartId, String appUserId) throws CartUserNotExistsException {
        if(cartStorage.findByIdAndAppUserId(cartId, appUserId).isEmpty()){
            throw new  CartUserNotExistsException(cartId, appUserId);
        };
    }
}
