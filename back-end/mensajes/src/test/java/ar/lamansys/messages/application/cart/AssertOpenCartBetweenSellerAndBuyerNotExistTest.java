package ar.lamansys.messages.application.cart;

import ar.lamansys.messages.application.cart.port.CartStorage;
import ar.lamansys.messages.application.exception.OpenCartException;
import ar.lamansys.messages.application.product.port.ProductStorage;
import ar.lamansys.messages.domain.cart.CartStoredBo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

public class AssertOpenCartBetweenSellerAndBuyerNotExistTest {

    private final CartStorage cartStorage = Mockito.mock(CartStorage.class);
    private final ProductStorage productStorage = Mockito.mock(ProductStorage.class);
    private final AssertOpenCartBetweenSellerAndBuyerNotExists assertOpenCartBetweenSellerAndBuyerNotExists = new AssertOpenCartBetweenSellerAndBuyerNotExists(productStorage, cartStorage);

    @Test
    void run_whenCartExistsBetweenBuyerAndSeller_shouldThrowOpenCartException() {
        // Arrange
        String userId = "buyer123";
        Integer productId = 1;
        String sellerId = "seller123";

        when(productStorage.getSellerByProductId(productId)).thenReturn(sellerId);
        when(cartStorage.getCartExists(userId, sellerId)).thenReturn(new CartStoredBo(1, userId, 1000, false, sellerId));

        // Act & Assert
        assertThatThrownBy(() -> assertOpenCartBetweenSellerAndBuyerNotExists.run(userId, productId))
                .isInstanceOf(OpenCartException.class)
                .hasMessageContaining("A cart already exists between you and the seller " + sellerId);
    }

    @Test
    void run_whenNoCartExistsBetweenBuyerAndSeller_shouldNotThrowAnyException() {
        // Arrange
        String userId = "buyer123";
        Integer productId = 1;
        String sellerId = "seller123";

        when(productStorage.getSellerByProductId(productId)).thenReturn(sellerId);
        when(cartStorage.getCartExists(userId, sellerId)).thenReturn(null);

        // Act & Assert
        assertOpenCartBetweenSellerAndBuyerNotExists.run(userId, productId);  // No exception should be thrown
    }
}