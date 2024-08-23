package ar.lamansys.messages.application.product;

import ar.lamansys.messages.application.exception.ProductNotExistsException;
import ar.lamansys.messages.application.product.port.ProductStorage;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

public class AssertProductExistsTest {

    private final ProductStorage productStorage = Mockito.mock(ProductStorage.class);
    private final AssertProductExists assertProductExists = new AssertProductExists(productStorage);

    @Test
    void run_whenProductDoesNotExist_shouldThrowProductNotExistsException() {
        // Arrange
        Integer productId = 1;
        when(productStorage.exists(productId)).thenReturn(false);

        // Act & Assert
        assertThatThrownBy(() -> assertProductExists.run(productId))
                .isInstanceOf(ProductNotExistsException.class)
                .hasMessageContaining("Product " + productId + " doesn't exist");
    }

    @Test
    void run_whenProductExists_shouldNotThrowAnyException() {
        // Arrange
        Integer productId = 1;
        when(productStorage.exists(productId)).thenReturn(true);

        // Act & Assert
        assertProductExists.run(productId);  // No exception should be thrown
    }
}
