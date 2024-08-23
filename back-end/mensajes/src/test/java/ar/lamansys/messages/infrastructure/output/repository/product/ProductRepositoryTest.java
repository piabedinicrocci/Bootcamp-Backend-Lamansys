package ar.lamansys.messages.infrastructure.output.repository.product;

import ar.lamansys.messages.domain.product.ProductStoredBo;
import ar.lamansys.messages.infrastructure.output.entity.Product;
import ar.lamansys.messages.infrastructure.output.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = false)
@EnableJpaRepositories(basePackages = {"ar.lamansys.messages.infrastructure.output"})
@EntityScan(basePackages = {"ar.lamansys.messages.infrastructure.output"})
public class ProductRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    private Product product1;
    private Product product2;
    private Product product3;

    @BeforeEach
    void setUp() {
        // Crea y persiste productos
        product1 = new Product();
        product1.setUserId("user1");
        product1.setName("Product1");
        product1.setStock(100);
        product1.setUnitPrice(1500);
        entityManager.persist(product1);

        product2 = new Product();
        product2.setUserId("user1");
        product2.setName("Product2");
        product2.setStock(230);
        product2.setUnitPrice(6300);
        entityManager.persist(product2);

        product3 = new Product();
        product3.setUserId("user2");
        product3.setName("Product3");
        product3.setStock(30);
        product3.setUnitPrice(300);
        entityManager.persist(product3);

        entityManager.flush(); // Asegúrate de que las entidades se persistan antes de las pruebas
    }

    @Test
    void findAllByUserId_empty_shouldBeEmpty() {
        assertThat(productRepository.findAllByUserId("nonexistent_user")).isEmpty();
    }

    @Test
    void findAllByUserId_withProducts_shouldReturnCorrectProducts() {
        // Act y Assert
        assertThat(productRepository.findAllByUserId("user1").collect(Collectors.toList()))
                .hasSize(2)
                .extracting("name")
                .containsExactlyInAnyOrder("Product1", "Product2");

        assertThat(productRepository.findAllByUserId("user2").collect(Collectors.toList()))
                .hasSize(1)
                .extracting("name")
                .containsExactly("Product3");

        assertThat(productRepository.findAllByUserId("nonexistent_user")).isEmpty();
    }

    @Test
    void findProductById_whenProductExists_shouldReturnProductStoredBo() {
        // Act
        Optional<ProductStoredBo> productStoredBo = productRepository.findProductById(product1.getId());

        // Assert
        assertThat(productStoredBo).isPresent();
        assertThat(productStoredBo.get().getName()).isEqualTo(product1.getName());
        assertThat(productStoredBo.get().getStock()).isEqualTo(product1.getStock());
        assertThat(productStoredBo.get().getUnitPrice()).isEqualTo(product1.getUnitPrice());
        assertThat(productStoredBo.get().getUserId()).isEqualTo(product1.getUserId());
    }

    @Test
    void getStock_whenProductExists_shouldReturnStock() {
        // Act
        Integer stock = productRepository.getStock(product1.getId());

        // Assert
        assertThat(stock).isEqualTo(product1.getStock());
    }

    @Test
    void getSellerByProductId_whenProductExists_shouldReturnUserId() {
        // Act
        String userId = productRepository.getSellerByProductId(product1.getId());

        // Assert
        assertThat(userId).isEqualTo(product1.getUserId());
    }

    @Test
    void getUnitPrice_whenProductExists_shouldReturnUnitPrice() {
        // Act
        Integer unitPrice = productRepository.getUnitPrice(product1.getId());

        // Assert
        assertThat(unitPrice).isEqualTo(product1.getUnitPrice());
    }

    @Test
    void findPriceByProductId_whenProductExists_shouldReturnUnitPrice() {
        // Act
        Integer unitPrice = productRepository.findPriceByProductId(product1.getId());

        // Assert
        assertThat(unitPrice).isEqualTo(product1.getUnitPrice());
    }

}
