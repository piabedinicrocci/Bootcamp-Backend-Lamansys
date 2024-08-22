package ar.lamansys.messages.application.cartProduct;

import ar.lamansys.messages.application.cart.AssertCartIsNotFinalized;
import ar.lamansys.messages.application.cart.AssertCartUserExist;
import ar.lamansys.messages.application.cart.port.CartStorage;
import ar.lamansys.messages.application.cartProduct.port.CartProductStorage;
import ar.lamansys.messages.application.exception.ProductNotExistsException;
import ar.lamansys.messages.application.exception.UserNotExistsException;
import ar.lamansys.messages.application.product.AssertProductExists;
import ar.lamansys.messages.application.product.AssertStockAvailable;
import ar.lamansys.messages.application.product.port.ProductStorage;
import ar.lamansys.messages.application.user.AssertUserExists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class AddProductToCartTest {
    @Mock
    private CartProductStorage cartProductStorage;

    @Mock
    private ProductStorage productStorage;

    @Mock
    private CartStorage cartStorage;

    @Mock
    private AssertUserExists assertUserExists;

    @Mock
    private AssertProductExists assertProductExists;

    @Mock
    private AssertCartUserExist assertCartUserExist;

    @Mock
    private AssertProductNotInCartExist assertProductNotInCartExist;

    @Mock
    private AssertStockAvailable assertStockAvailable;

    @Mock
    private AssertProductIsFromSameSeller assertProductIsFromSameSeller;

    @Mock
    private AssertCartIsNotFinalized assertCartIsNotFinalized;

    @InjectMocks
    private AddProductToCart addProductToCart;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void run_whenAllAssertionsPass_shouldAddProductToCartAndUpdateTotalPrice() throws Exception {
        // Arrange
        Integer cartId = 50;
        Integer productId = 30;
        String userId = "user13";
        Integer quantity = 2;
        Integer unitPrice = 500;
        Integer quantityPrice = quantity * unitPrice;
        Integer newTotalPrice = 2000;

        when(productStorage.getUnitPrice(productId)).thenReturn(unitPrice);
        when(cartProductStorage.calculateTotalPrice(cartId)).thenReturn(newTotalPrice);

        // Act
        addProductToCart.run(cartId, productId, userId, quantity);

        // Assert
        verify(assertUserExists).run(userId);
        verify(assertProductExists).run(productId);
        verify(assertCartIsNotFinalized).run(cartId);
        verify(assertCartUserExist).run(cartId, userId);
        verify(assertProductIsFromSameSeller).run(cartId, productId);
        verify(assertProductNotInCartExist).run(cartId, productId);
        verify(assertStockAvailable).run(productId, quantity);
        verify(cartProductStorage).addProductToCart(cartId, productId, quantity, quantityPrice);
        verify(cartStorage).updateTotalPrice(cartId, newTotalPrice);
    }

}

