package ar.lamansys.messages.application.product;

import ar.lamansys.messages.application.exception.ProductNotExistsException;
import ar.lamansys.messages.application.exception.StockNotAvailableException;
import ar.lamansys.messages.application.product.port.ProductStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AssertStockAvailable {
    private ProductStorage productStorage;

    public void run(Integer productId, Integer quantity) throws StockNotAvailableException {
        if ( productStorage.getStock(productId) < quantity) {
            throw new StockNotAvailableException(productId,quantity);
        }
    }
}
