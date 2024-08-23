package ar.lamansys.messages.application.product;

import ar.lamansys.messages.application.exception.StockNotAvailableException;
import ar.lamansys.messages.application.product.port.ProductStorage;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

public class AssertStockAvailableTest {

    private final ProductStorage productStorage = Mockito.mock(ProductStorage.class);
    private final AssertStockAvailable assertStockAvailable = new AssertStockAvailable(productStorage);

    @Test
    void run_whenStockIsInsufficient_shouldThrowStockNotAvailableException() {
        // Arrange
        Integer productId = 1;
        Integer quantity = 10;
        Integer availableStock = 5;
        when(productStorage.getStock(productId)).thenReturn(availableStock);

        // Act & Assert
        assertThatThrownBy(() -> assertStockAvailable.run(productId, quantity))
                .isInstanceOf(StockNotAvailableException.class)
                .hasMessageContaining(String.format("There is not enough stock to supply the quantity of %d for the product with id %d. %d quantities are missing",
                        quantity, productId, (quantity - availableStock)));
    }

    @Test
    void run_whenStockIsSufficient_shouldNotThrowAnyException() {
        // Arrange
        Integer productId = 1;
        Integer quantity = 10;
        Integer availableStock = 15;
        when(productStorage.getStock(productId)).thenReturn(availableStock);

        // Act & Assert
        assertStockAvailable.run(productId, quantity);  // No exception should be thrown
    }
}
