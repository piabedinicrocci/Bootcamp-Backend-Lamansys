package ar.lamansys.messages.infrastructure.output.repository.cartProduct;

import ar.lamansys.messages.domain.cart.ProductShowCartBo;
import ar.lamansys.messages.domain.cartProduct.CartProductBo;
import ar.lamansys.messages.infrastructure.output.entity.Cart;
import ar.lamansys.messages.infrastructure.output.entity.CartProduct;
import ar.lamansys.messages.infrastructure.output.entity.CartProductId;
import ar.lamansys.messages.infrastructure.output.entity.Product;
import ar.lamansys.messages.infrastructure.output.repository.CartProductRepository;
import ar.lamansys.messages.infrastructure.output.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = false)
@EnableJpaRepositories(basePackages = {"ar.lamansys.messages.infrastructure.output"})
@EntityScan(basePackages = {"ar.lamansys.messages.infrastructure.output"})
public class CartProductRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CartProductRepository cartProductRepository;

    @Autowired
    private ProductRepository productRepository;

    private Cart testCart;
    private Product testProduct;
    private CartProduct testCartProduct;

    @BeforeEach
    void setUp() {
        testCart = newCart("user123", 300, false, "seller123");
        entityManager.persistAndFlush(testCart);

        testProduct = newProduct("Product1", 1000, 150, "seller123");
        entityManager.persistAndFlush(testProduct);

        testCartProduct = newCartProduct(testCart.getId(), testProduct.getId(), 2, 300);
        entityManager.persistAndFlush(testCartProduct);
    }

    @Test
    void findAllByCartId_whenProductsExist_shouldReturnProductShowCartBoStream() {
        //act
        try (Stream<ProductShowCartBo> productsStream = cartProductRepository.findAllByCartId(testCart.getId())) {
            //assert
            assertThat(productsStream).hasSize(1).first().satisfies(productShowCartBo -> {
                assertThat(productShowCartBo.getProductId()).isEqualTo(testProduct.getId());
                assertThat(productShowCartBo.getName()).isEqualTo("Product1");
                assertThat(productShowCartBo.getUnitPrice()).isEqualTo(150);
                assertThat(productShowCartBo.getQuantity()).isEqualTo(2);
                assertThat(productShowCartBo.getQuantityPrice()).isEqualTo(300);
            });
        }
    }

    @Test
    void updateQuantity_whenCartProductExists_shouldUpdateQuantity() {
        //act
        cartProductRepository.updateQuantity(5, testCart.getId(), testProduct.getId(), 750);

        //assert
        CartProduct updatedCartProduct = entityManager.find(CartProduct.class, new CartProductId(testCart.getId(), testProduct.getId()));
        assertThat(updatedCartProduct).satisfies(cartProduct -> {
            assertThat(cartProduct.getQuantity()).isEqualTo(2);
            assertThat(cartProduct.getQuantityPrice()).isEqualTo(300);
        });
    }

    @Test
    void findByCartIdAndProductId_whenCartProductExists_shouldReturnCartProductBo() {
        //act and assert
        CartProductBo cartProductBo = cartProductRepository.findByCartIdAndProductId(testCart.getId(), testProduct.getId());
        assertThat(cartProductBo).satisfies(cartProduct -> {
            assertThat(cartProduct.getCartId()).isEqualTo(testCart.getId());
            assertThat(cartProduct.getProductId()).isEqualTo(testProduct.getId());
            assertThat(cartProduct.getQuantity()).isEqualTo(2);
            assertThat(cartProduct.getQuantityPrice()).isEqualTo(300);
        });
    }

    @Test
    void calculateTotalPrice_whenProductsInCart_shouldReturnTotalPrice() {
        //act and assert
        assertThat(cartProductRepository.calculateTotalPrice(testCart.getId())).isEqualTo(300);
    }

    @Test
    void addProductToCart_whenProductIsAdded_shouldIncreaseProductCount() {
        //act
        cartProductRepository.addProductToCart(testCart.getId(), 2, 3, 450);

        //assert
        assertThat(cartProductRepository.countByCartId(testCart.getId())).isEqualTo(2);
    }

    @Test
    void deleteProductFromCart_whenProductExists_shouldRemoveProduct() {
        //act
        cartProductRepository.deleteProductFromCart(testCart.getId(), testProduct.getId());

        //assert
        assertThat(cartProductRepository.countByCartId(testCart.getId())).isEqualTo(0);
    }

    private static Cart newCart(String appUserId, int totalPrice, boolean isFinalized, String sellerId) {
        return new Cart(null, appUserId, totalPrice, isFinalized, sellerId);
    }

    private static Product newProduct(String name, int stock, int unitPrice, String userId) {
        return new Product(null, name, stock, unitPrice, userId);
    }

    private static CartProduct newCartProduct(Integer cartId, Integer productId, int quantity, int quantityPrice) {
        return new CartProduct(new CartProductId(cartId, productId), quantity, quantityPrice);
    }
}
