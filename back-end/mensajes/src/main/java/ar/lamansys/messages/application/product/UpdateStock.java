package ar.lamansys.messages.application.product;

import ar.lamansys.messages.application.exception.ProductNotExistsException;
import ar.lamansys.messages.application.exception.UserNotExistsException;
import ar.lamansys.messages.application.exception.ProductIsNotFromSellerException;
import ar.lamansys.messages.application.product.port.ProductStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Transactional
@Service
public class UpdateStock {
    private final ProductStorage productStorage;
    private final AssertProductIsFromSeller assertProductIsFromSeller;

    public void run(String appUserId, Integer productId, Integer newStock) throws ProductIsNotFromSellerException, UserNotExistsException, ProductNotExistsException {
        //verificar que el producto y usuario existan, y que el producto pertenezca al vendedor
        assertProductIsFromSeller.run(productId,appUserId);

        //actualizo el stock en product storage
        productStorage.updateStock(productId, newStock);
    }

}
