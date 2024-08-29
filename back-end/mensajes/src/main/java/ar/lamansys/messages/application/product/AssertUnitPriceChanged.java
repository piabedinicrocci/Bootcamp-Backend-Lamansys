package ar.lamansys.messages.application.product;

import ar.lamansys.messages.application.exception.UnitPriceHasNotChangedException;
import ar.lamansys.messages.application.product.port.ProductStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@AllArgsConstructor
@Service
public class AssertUnitPriceChanged {
    private final ProductStorage productStorage;

    public void run(Integer productId, Integer newPrice) throws UnitPriceHasNotChangedException {
        if (Objects.equals(productStorage.getUnitPrice(productId), newPrice)) {
            throw new UnitPriceHasNotChangedException(productId,newPrice);
        }
    }
}
