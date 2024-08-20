package ar.lamansys.messages.application.cart;

import ar.lamansys.messages.application.cart.port.CartStorage;
import ar.lamansys.messages.application.exception.OpenCartException;
import ar.lamansys.messages.application.exception.StockNotAvailableException;
import ar.lamansys.messages.application.product.AssertProductExists;
import ar.lamansys.messages.application.product.AssertStockAvailable;
import ar.lamansys.messages.application.product.port.ProductStorage;
import ar.lamansys.messages.application.user.AssertUserExists;
import ar.lamansys.messages.application.exception.ProductNotExistsException;
import ar.lamansys.messages.application.exception.UserNotExistsException;
import ar.lamansys.messages.domain.cart.CartStoredBo;
import ar.lamansys.messages.domain.cart.NewCartBo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CreateCart {
    private final CartStorage cartStorage;
    private final AssertUserExists assertUserExists;
    private final AssertProductExists assertProductExists;
    private final AssertStockAvailable assertStockAvailable;
    private final AssertOpenCartBetweenSellerAndBuyerNotExists assertOpenCartBetweenSellerAndBuyerNotExists;
    private final ProductStorage productStorage;


    public void run(String userId, NewCartBo cartBO) throws UserNotExistsException, ProductNotExistsException, StockNotAvailableException, OpenCartException {
        assertUserExists.run(userId);
        assertProductExists.run(cartBO.getProductId());
        assertStockAvailable.run(cartBO.getProductId(), cartBO.getQuantity());
        assertOpenCartBetweenSellerAndBuyerNotExists.run(userId,cartBO.getProductId());

        String sellerId = productStorage.getSellerByProductId(cartBO.getProductId());

        Integer unitPrice = productStorage.getUnitPrice(cartBO.getProductId());
        Integer totalPrice = cartBO.getQuantity() * unitPrice;
        cartStorage.createCart(userId, totalPrice, false, sellerId);
    }
}
