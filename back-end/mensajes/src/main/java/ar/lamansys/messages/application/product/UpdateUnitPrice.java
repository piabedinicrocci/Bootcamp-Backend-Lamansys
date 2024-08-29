package ar.lamansys.messages.application.product;

import ar.lamansys.messages.application.exception.ProductIsNotFromSellerException;
import ar.lamansys.messages.application.exception.ProductNotExistsException;
import ar.lamansys.messages.application.exception.UserNotExistsException;
import ar.lamansys.messages.application.product.port.ProductStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@AllArgsConstructor
@Transactional
@Service
public class UpdateUnitPrice {
    private final ProductStorage productStorage;
    private final AssertProductIsFromSeller assertProductIsFromSeller;
    private final AssertUnitPriceChanged assertUnitPriceChanged;

    public void run(String appUserId, Integer productId, Integer newPrice) throws ProductIsNotFromSellerException, UserNotExistsException, ProductNotExistsException {
        //verificar que el producto y usuario existan, y que el producto pertenezca al vendedor
        assertProductIsFromSeller.run(productId,appUserId);

        //verificar que el precio unitario a modificar efectivamente sea distinto al actual
        assertUnitPriceChanged.run(productId,newPrice);

        //actualizo el precio unitario en product storage
        productStorage.updateUnitPrice(productId, newPrice);
    }

}
