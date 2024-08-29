package ar.lamansys.messages.application.product;

import ar.lamansys.messages.application.exception.StockHasNotChangedException;
import ar.lamansys.messages.application.product.port.ProductStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@AllArgsConstructor
@Service
public class AssertStockChanged {
    private final ProductStorage productStorage;

    public void run(Integer productId, Integer newStock) throws StockHasNotChangedException {
        if (Objects.equals(productStorage.getStock(productId), newStock)) {
            throw new StockHasNotChangedException(productId,newStock);
        }
    }
}
