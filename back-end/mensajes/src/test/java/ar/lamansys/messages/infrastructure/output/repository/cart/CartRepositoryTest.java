package ar.lamansys.messages.infrastructure.output.repository.cart;

import ar.lamansys.messages.infrastructure.output.entity.Cart;
import ar.lamansys.messages.infrastructure.output.repository.CartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest(showSql = false)
@EnableJpaRepositories(basePackages = {"ar.lamansys.messages.infrastructure.output"})
@EntityScan(basePackages = {"ar.lamansys.messages.infrastructure.output"})
public class CartRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CartRepository cartRepository;

    private Cart testCart;

    @BeforeEach
    void setUp() {
        testCart = newCart("user123", 1000, false, "seller123");
        entityManager.persistAndFlush(testCart);
    }

    @Test
    void findByAppUserIdAndSellerId_whenCartExists_shouldReturnCart() {
        //act & assert
        assertThat(
                cartRepository.findByAppUserIdAndSellerId("user123", "seller123")
        ).isPresent().get().satisfies(cart -> {
            assertThat(cart.getAppUserId()).isEqualTo("user123");
            assertThat(cart.getSellerId()).isEqualTo("seller123");
        });
    }

    @Test
    void getCartExists_whenCartExists_shouldReturnCartStoredBo() {
        //act and assert
        assertThat(
                cartRepository.getCartExists("user123", "seller123")
        ).satisfies(cartStoredBo -> {
            assertThat(cartStoredBo.getId()).isEqualTo(testCart.getId());
            assertThat(cartStoredBo.getAppUserId()).isEqualTo("user123");
            assertThat(cartStoredBo.getTotalPrice()).isEqualTo(1000);
            assertThat(cartStoredBo.getIsFinalized()).isFalse();
            assertThat(cartStoredBo.getSellerId()).isEqualTo("seller123");
        });
    }

    @Test
    void findByIdAndAppUserId_whenCartExists_shouldReturnCart() {
        //act & Assert
        assertThat(
                cartRepository.findByIdAndAppUserId(testCart.getId(), "user123")
        ).isPresent().get().satisfies(cart -> {
            assertThat(cart.getId()).isEqualTo(testCart.getId());
            assertThat(cart.getAppUserId()).isEqualTo("user123");
        });
    }

    @Test
    void updateTotalPrice_whenCartExists_shouldUpdateTotalPrice() {
        //act
        cartRepository.updateTotalPrice(testCart.getId(), 2000);

        //assert
        assertThat(
                cartRepository.getTotalPrice(testCart.getId())
        ).isEqualTo(2000);
    }

    @Test
    void getTotalPrice_whenCartExists_shouldReturnTotalPrice() {
        //act and assert
        assertThat(
                cartRepository.getTotalPrice(testCart.getId())
        ).isEqualTo(1000);
    }

    @Test
    void getSellerById_whenCartExists_shouldReturnSellerId() {
        //Act and assert
        assertThat(
                cartRepository.getSellerById(testCart.getId())
        ).isEqualTo("seller123");
    }

    @Test
    void getIsFinalizedById_whenCartExists_shouldReturnIsFinalized() {
        //act and assert
        assertThat(
                cartRepository.getIsFinalizedById(testCart.getId())
        ).isFalse();
    }

    @Test
    void updateIsFinalized_whenCartExists_shouldUpdateIsFinalized() {
        //act
        cartRepository.updateIsFinalized(testCart.getId());

        //assert
        assertThat(
                cartRepository.getIsFinalizedById(testCart.getId())
        ).isTrue();
    }

    private static Cart newCart(String appUserId, int totalPrice, boolean isFinalized, String sellerId) {
        return new Cart(null, appUserId, totalPrice, isFinalized, sellerId);
    }
}
