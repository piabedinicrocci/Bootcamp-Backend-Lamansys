package ar.lamansys.messages.application.product;

import ar.lamansys.messages.application.exception.UserNotExistsException;
import ar.lamansys.messages.application.product.port.ProductStorage;
import ar.lamansys.messages.application.user.AssertUserExists;
import ar.lamansys.messages.domain.product.ProductStoredBo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ListProductsTest {

    @Mock
    private ProductStorage productStorage;

    @Mock
    private AssertUserExists assertUserExists;

    @InjectMocks
    private ListProducts listProducts;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void run_userExists_returnsProductList() throws UserNotExistsException {
        // Arrange
        String userId = "user1";
        List<ProductStoredBo> expectedProducts = List.of(
                new ProductStoredBo( "Product1", 40, 600, userId)
        );
        doNothing().when(assertUserExists).run(userId);
        when(productStorage.findAllByUserId(userId)).thenReturn(expectedProducts.stream());

        // Act
        List<ProductStoredBo> result = listProducts.run(userId);

        // Assert
        assertEquals(expectedProducts, result);
        verify(assertUserExists).run(userId);
        verify(productStorage).findAllByUserId(userId);
    }
    @Test
    void run_userDoesNotExist_throwsUserNotExistsException() throws UserNotExistsException {
        // Arrange
        String userId = "nonExistentUser";
        doThrow(new UserNotExistsException(userId)).when(assertUserExists).run(userId);

        // Act & Assert
        UserNotExistsException exception = assertThrows(UserNotExistsException.class, () -> listProducts.run(userId));
        assertEquals("User "+ userId +" don't exists", exception.getMessage());
        verify(assertUserExists).run(userId);
        verify(productStorage, never()).findAllByUserId(anyString());
    }
    @Test
    void run_userExistsButNoProducts_returnsEmptyList() throws UserNotExistsException {
        // Arrange
        String userId = "user2";
        doNothing().when(assertUserExists).run(userId);
        when(productStorage.findAllByUserId(userId)).thenReturn(Stream.empty());

        // Act
        List<ProductStoredBo> result = listProducts.run(userId);

        // Assert
        assertTrue(result.isEmpty());
        verify(assertUserExists).run(userId);
        verify(productStorage).findAllByUserId(userId);
    }

    @Test
    void run_returnsAllProducts() {
        // Arrange
        List<ProductStoredBo> expectedProducts = List.of(
                new ProductStoredBo("Product1", 40, 600, "user1"),
                new ProductStoredBo("Product2", 30, 400, "user2")
        );
        when(productStorage.findAllProducts()).thenReturn(expectedProducts.stream());

        // Act
        List<ProductStoredBo> result = listProducts.run();

        // Assert
        assertEquals(expectedProducts, result);
        verify(productStorage).findAllProducts();
    }




}
