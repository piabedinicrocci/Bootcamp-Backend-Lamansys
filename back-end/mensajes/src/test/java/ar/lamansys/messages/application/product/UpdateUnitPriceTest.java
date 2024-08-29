package ar.lamansys.messages.application.product;

import ar.lamansys.messages.application.exception.ProductIsNotFromSellerException;
import ar.lamansys.messages.application.exception.ProductNotExistsException;
import ar.lamansys.messages.application.exception.UserNotExistsException;
import ar.lamansys.messages.application.product.port.ProductStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

class UpdateUnitPriceTest {

    @Mock
    private ProductStorage productStorage;

    @Mock
    private AssertProductIsFromSeller assertProductIsFromSeller;

    @Mock
    private AssertUnitPriceChanged assertUnitPriceChanged;

    @InjectMocks
    private UpdateUnitPrice updateUnitPrice;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void run_ok() throws Exception {
        // Arrange
        String appUserId = "user1";
        Integer productId = 1;
        Integer newPrice = 1000;

        doNothing().when(assertProductIsFromSeller).run(productId, appUserId);
        doNothing().when(assertUnitPriceChanged).run(productId, newPrice);
        doNothing().when(productStorage).updateUnitPrice(productId, newPrice);

        // Act
        updateUnitPrice.run(appUserId, productId, newPrice);

        // Assert
        verify(assertProductIsFromSeller).run(productId, appUserId);
        verify(assertUnitPriceChanged).run(productId, newPrice);
        verify(productStorage).updateUnitPrice(productId, newPrice);
    }

    @Test
    void run_productNotFromSeller() throws Exception {
        // Arrange
        String appUserId = "user2";
        Integer productId = 1;
        Integer newPrice = 1000;

        doThrow(new ProductIsNotFromSellerException(productId, appUserId)).when(assertProductIsFromSeller).run(productId, appUserId);

        // Act & Assert
        assertThrows(ProductIsNotFromSellerException.class, () -> updateUnitPrice.run(appUserId, productId, newPrice));
    }

    @Test
    void run_productNotExists() throws Exception {
        // Arrange
        String appUserId = "user1";
        Integer productId = 999;
        Integer newPrice = 1000;

        doThrow(new ProductNotExistsException(productId)).when(assertProductIsFromSeller).run(productId, appUserId);

        // Act & Assert
        assertThrows(ProductNotExistsException.class, () -> updateUnitPrice.run(appUserId, productId, newPrice));
    }

    @Test
    void run_userNotExists() throws Exception {
        // Arrange
        String appUserId = "invalidUser";
        Integer productId = 1;
        Integer newPrice = 1000;

        doThrow(new UserNotExistsException(appUserId)).when(assertProductIsFromSeller).run(productId, appUserId);

        // Act & Assert
        assertThrows(UserNotExistsException.class, () -> updateUnitPrice.run(appUserId, productId, newPrice));
    }
}
