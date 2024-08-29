package ar.lamansys.messages.application.product;

import ar.lamansys.messages.application.exception.StockHasNotChangedException;
import ar.lamansys.messages.application.product.port.ProductStorage;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

public class AssertStockChangedTest {

    private final ProductStorage productStorage = Mockito.mock(ProductStorage.class);
    private final AssertStockChanged assertStockChanged = new AssertStockChanged(productStorage);

    @Test
    void run_whenStockHasNotChanged_shouldThrowStockHasNotChangedException() {
        // Arrange
        Integer productId = 1;
        Integer newStock = 50;
        when(productStorage.getStock(productId)).thenReturn(newStock);

        // Act & Assert
        assertThatThrownBy(() -> assertStockChanged.run(productId, newStock))
                .isInstanceOf(StockHasNotChangedException.class)
                .hasMessageContaining(String.format("The stock of the product with id %d is already %d", productId, newStock));
    }

    @Test
    void run_whenStockHasChanged_shouldNotThrowAnyException() {
        // Arrange
        Integer productId = 1;
        Integer newStock = 50;
        Integer currentStock = 40;
        when(productStorage.getStock(productId)).thenReturn(currentStock);

        // Act & Assert
        assertStockChanged.run(productId, newStock);
    }
}
