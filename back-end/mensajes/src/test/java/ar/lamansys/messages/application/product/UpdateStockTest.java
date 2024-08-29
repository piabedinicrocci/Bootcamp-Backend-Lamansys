package ar.lamansys.messages.application.product;

import ar.lamansys.messages.application.exception.ProductNotExistsException;
import ar.lamansys.messages.application.exception.UserNotExistsException;
import ar.lamansys.messages.application.exception.ProductIsNotFromSellerException;
import ar.lamansys.messages.application.product.port.ProductStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

public class UpdateStockTest {

    @Mock
    private ProductStorage productStorage;

    @Mock
    private AssertProductIsFromSeller assertProductIsFromSeller;

    @Mock
    private AssertStockChanged assertStockChanged;

    @InjectMocks
    private UpdateStock updateStock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void run_stockUpdatedSuccessfully() throws UserNotExistsException, ProductNotExistsException, ProductIsNotFromSellerException {
        // Arrange
        String userId = "user1";
        Integer productId = 1;
        Integer newStock = 50;

        doNothing().when(assertProductIsFromSeller).run(productId, userId);
        doNothing().when(assertStockChanged).run(productId, newStock);
        doNothing().when(productStorage).updateStock(productId, newStock);

        // Act
        updateStock.run(userId, productId, newStock);

        // Assert
        verify(assertProductIsFromSeller).run(productId, userId);
        verify(assertStockChanged).run(productId, newStock);
        verify(productStorage).updateStock(productId, newStock);
    }

    @Test
    void run_productNotExists() throws UserNotExistsException, ProductNotExistsException, ProductIsNotFromSellerException {
        // Arrange
        String userId = "user1";
        Integer productId = 1;
        Integer newStock = 50;

        doThrow(new ProductNotExistsException(productId)).when(assertProductIsFromSeller).run(productId, userId);

        // Act & Assert
        org.junit.jupiter.api.Assertions.assertThrows(ProductNotExistsException.class, () -> {
            updateStock.run(userId, productId, newStock);
        });
    }

    @Test
    void run_userNotExists() throws UserNotExistsException, ProductNotExistsException, ProductIsNotFromSellerException {
        // Arrange
        String userId = "invalidUser";
        Integer productId = 1;
        Integer newStock = 50;

        doThrow(new UserNotExistsException(userId)).when(assertProductIsFromSeller).run(productId, userId);

        // Act & Assert
        org.junit.jupiter.api.Assertions.assertThrows(UserNotExistsException.class, () -> {
            updateStock.run(userId, productId, newStock);
        });
    }

    @Test
    void run_productNotFromSeller() throws UserNotExistsException, ProductNotExistsException, ProductIsNotFromSellerException {
        // Arrange
        String userId = "user1";
        Integer productId = 1;
        Integer newStock = 50;

        doThrow(new ProductIsNotFromSellerException(productId, userId)).when(assertProductIsFromSeller).run(productId, userId);

        // Act & Assert
        org.junit.jupiter.api.Assertions.assertThrows(ProductIsNotFromSellerException.class, () -> {
            updateStock.run(userId, productId, newStock);
        });
    }
}
