package ar.lamansys.messages.infrastructure.output.repository.cart;

import ar.lamansys.messages.domain.cart.CartStoredBo;
import ar.lamansys.messages.infrastructure.output.entity.Cart;
import ar.lamansys.messages.infrastructure.output.repository.CartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest(showSql = false)
@EnableJpaRepositories(basePackages = {"ar.lamansys.messages.infrastructure.output"})
@EntityScan(basePackages = {"ar.lamansys.messages.infrastructure.output"})
public class CartRepositoryTest {
    @Autowired
    private CartRepository cartRepository;
    private Cart testCart;

    @BeforeEach
    void setUp() {
        testCart = new Cart(1, "user123", 1000, false, "seller123");
        cartRepository.save(testCart);
    }

    @Test
    void findByAppUserIdAndSellerId_whenCartExists_shouldReturnCart() {
        // Act
        Optional<Cart> result = cartRepository.findByAppUserIdAndSellerId("user123", "seller123");

        // Assert
        assertThat(result).isPresent();
        assertThat(result.get().getAppUserId()).isEqualTo("user123");
        assertThat(result.get().getSellerId()).isEqualTo("seller123");
    }

    @Test
    void getCartExists_whenCartExists_shouldReturnCartStoredBo() {

        // Act
        CartStoredBo result = cartRepository.getCartExists("user123", "seller123");

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getAppUserId()).isEqualTo("user123");
        assertThat(result.getTotalPrice()).isEqualTo(1000);
        assertThat(result.getIsFinalized()).isFalse();
        assertThat(result.getSellerId()).isEqualTo("seller123");
    }

    @Test
    void findByIdAndAppUserId_whenCartExists_shouldReturnCart() {

        // Act
        Optional<Cart> result = cartRepository.findByIdAndAppUserId(1, "user123");

        // Assert
        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(1);
        assertThat(result.get().getAppUserId()).isEqualTo("user123");
    }

    @Test
    void updateTotalPrice_whenCartExists_shouldUpdateTotalPrice() {

        // Act
        cartRepository.updateTotalPrice(1, 2000);

        // Assert
        Integer updatedTotalPrice = cartRepository.getTotalPrice(1);
        assertThat(updatedTotalPrice).isEqualTo(2000);
    }

    @Test
    void getTotalPrice_whenCartExists_shouldReturnTotalPrice() {
        // Act
        Integer result = cartRepository.getTotalPrice(1);

        // Assert
        assertThat(result).isEqualTo(1000);
    }

    @Test
    void getSellerById_whenCartExists_shouldReturnSellerId() {
        // Act
        String result = cartRepository.getSellerById(1);
        // Assert
        assertThat(result).isEqualTo("seller123");
    }

    @Test
    void getIsFinalizedById_whenCartExists_shouldReturnIsFinalized() {
        // Act
        boolean result = cartRepository.getIsFinalizedById(1);
        // Assert
        assertThat(result).isFalse();
    }

    @Test
    void updateIsFinalized_whenCartExists_shouldUpdateIsFinalized() {
        // Act
        cartRepository.updateIsFinalized(1);
        // Assert
        boolean updatedIsFinalized = cartRepository.getIsFinalizedById(1);
        assertThat(updatedIsFinalized).isTrue();
    }
}
