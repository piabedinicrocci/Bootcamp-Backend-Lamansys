package ar.lamansys.messages.application.cartProduct;

import ar.lamansys.messages.application.cartProduct.port.CartProductStorage;
import ar.lamansys.messages.application.exception.ProductNotInCartException;
import ar.lamansys.messages.domain.cartProduct.CartProductBo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

public class AssertProductInCartExistTest {
    @Mock
    private CartProductStorage cartProductStorage;

    @InjectMocks
    private AssertProductInCartExist assertProductInCartExist;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void run_whenProductNotInCart_shouldThrowProductNotInCartException() {
        // Arrange
        Integer cartId = 1;
        Integer productId = 100;
        when(cartProductStorage.findByCartIdAndProductId(cartId, productId)).thenReturn(null);

        // Act & Assert
        assertThatThrownBy(() -> assertProductInCartExist.run(cartId, productId))
                .isInstanceOf(ProductNotInCartException.class)
                .hasMessageContaining("Product with id "+productId+" is not in cart "+cartId);
    }

    @Test
    void run_whenProductInCart_shouldNotThrowAnyException() {
        // Arrange
        Integer cartId = 23;
        Integer productId = 152;
        CartProductBo cartProductBo = new CartProductBo(cartId, productId, 32, 20140);

        when(cartProductStorage.findByCartIdAndProductId(cartId, productId)).thenReturn(cartProductBo);

        // Act & Assert
        assertProductInCartExist.run(cartId, productId);
    }
}
