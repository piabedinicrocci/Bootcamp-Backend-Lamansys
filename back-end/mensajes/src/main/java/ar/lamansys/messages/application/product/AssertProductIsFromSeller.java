package ar.lamansys.messages.application.product;

import ar.lamansys.messages.application.exception.ProductNotExistsException;
import ar.lamansys.messages.application.exception.UserNotExistsException;
import ar.lamansys.messages.application.exception.ProductIsNotFromSellerException;
import ar.lamansys.messages.application.product.port.ProductStorage;
import ar.lamansys.messages.application.user.port.UserStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AssertProductIsFromSeller {
    private final ProductStorage productStorage;
    private final UserStorage userStorage;

    public void run(Integer productId, String appUserId) throws ProductNotExistsException, UserNotExistsException, ProductIsNotFromSellerException {
        if (!productStorage.exists(productId)) {
            throw new ProductNotExistsException(productId);
        }
        if (!userStorage.exists(appUserId)) {
            throw new UserNotExistsException(appUserId);
        }
        if (!productStorage.getSellerByProductId(productId).equals(appUserId)) {
            throw new ProductIsNotFromSellerException(productId, appUserId);
        }
    }
}
