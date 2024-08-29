package ar.lamansys.messages.application.product;

import ar.lamansys.messages.application.exception.UnitPriceHasNotChangedException;
import ar.lamansys.messages.application.product.port.ProductStorage;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

public class AssertUnitPriceChangedTest {

    private final ProductStorage productStorage = Mockito.mock(ProductStorage.class);
    private final AssertUnitPriceChanged assertUnitPriceChanged = new AssertUnitPriceChanged(productStorage);

    @Test
    void run_whenUnitPriceHasNotChanged_shouldThrowUnitPriceHasNotChangedException() {
        // Arrange
        Integer productId = 1;
        Integer newPrice = 100;
        when(productStorage.getUnitPrice(productId)).thenReturn(newPrice);

        // Act & Assert
        assertThatThrownBy(() -> assertUnitPriceChanged.run(productId, newPrice))
                .isInstanceOf(UnitPriceHasNotChangedException.class)
                .hasMessageContaining(String.format("The price of the product with id %d is already %d", productId, newPrice));
    }

    @Test
    void run_whenUnitPriceHasChanged_shouldNotThrowAnyException() {
        // Arrange
        Integer productId = 1;
        Integer newPrice = 100;
        Integer currentPrice = 80;
        when(productStorage.getUnitPrice(productId)).thenReturn(currentPrice);

        // Act & Assert
        assertUnitPriceChanged.run(productId, newPrice);  // No exception should be thrown
    }
}
