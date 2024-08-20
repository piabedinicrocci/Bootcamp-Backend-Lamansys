package ar.lamansys.messages.application.product;

import ar.lamansys.messages.application.exception.ProductNotExistsException;
import ar.lamansys.messages.application.exception.UserNotExistsException;
import ar.lamansys.messages.application.product.port.ProductStorage;
import ar.lamansys.messages.application.user.port.UserStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service

public class AssertProductExists {
    private ProductStorage productStorage;

    public void run(Integer productId) throws ProductNotExistsException {
        if ( ! productStorage.exists(productId) ) {
            throw new ProductNotExistsException(productId);
        }
    }
}
