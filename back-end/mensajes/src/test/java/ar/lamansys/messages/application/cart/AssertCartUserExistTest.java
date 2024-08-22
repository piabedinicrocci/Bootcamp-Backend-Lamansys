package ar.lamansys.messages.application.cart;

import ar.lamansys.messages.application.cart.port.CartStorage;
import ar.lamansys.messages.application.exception.CartUserNotExistsException;
import ar.lamansys.messages.domain.cart.CartStoredBo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

public class AssertCartUserExistTest {
    private final CartStorage cartStorage= Mockito.mock(CartStorage.class);
    private final AssertCartUserExist assertCartUserExist=new AssertCartUserExist(cartStorage);

    @Test
    void run_whenCartDoesNotExistForUser_shouldThrowCartUserNotExistsException() {
        // Arrange
        Integer cartId = 1;
        String appUserId = "user123";
        when(cartStorage.findByIdAndAppUserId(cartId, appUserId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> assertCartUserExist.run(cartId, appUserId))
                .isInstanceOf(CartUserNotExistsException.class)
                .hasMessageContaining("A cart with ID " + cartId+ " doesn´t exists for user with id "+appUserId);
    }


    @Test
    void run_whenCartExistsForUser_shouldNotThrowAnyException() {
        // Arrange
        Integer cartId = 1;
        String appUserId = "user123";
        CartStoredBo cartStoredBo = new CartStoredBo(cartId, appUserId, 2000, false, "user3");
        when(cartStorage.findByIdAndAppUserId(cartId, appUserId)).thenReturn(Optional.of(cartStoredBo));

        // Act & Assert
        assertCartUserExist.run(cartId, appUserId);
    }
}
