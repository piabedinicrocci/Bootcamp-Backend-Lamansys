package ar.lamansys.messages.infrastructure.output.repository.product;

import ar.lamansys.messages.infrastructure.output.entity.Product;
import ar.lamansys.messages.infrastructure.output.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

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

    @Test
    void findAllByUserId_empty_shouldBeEmpty() {
        assertThat(productRepository.findAllByUserId("nonexistent_user"))
                .isEmpty();
    }

    @Test
    void findAllByUserId_withProducts_shouldReturnCorrectProducts() {
        // Arrange
        entityManager.persist(newProduct("user1", "Product1", 100, 1500));
        entityManager.persist(newProduct("user1", "Product2", 230, 6300));
        entityManager.persist(newProduct("user2", "Product3", 30, 300));

        // Act y Assert
        assertThat(productRepository.findAllByUserId("user1").collect(Collectors.toList()))
                .hasSize(2)
                .extracting("name")
                .containsExactlyInAnyOrder("Product1", "Product2");

        assertThat(productRepository.findAllByUserId("user2").collect(Collectors.toList()))
                .hasSize(1)
                .extracting("name")
                .containsExactly("Product3");

        assertThat(productRepository.findAllByUserId("nonexistent_user"))
                .isEmpty();
    }

    private static Product newProduct(String userId, String name, int stock, int unitPrice) {
        Product product = new Product();
        product.setUserId(userId);
        product.setName(name);
        product.setStock(stock);
        product.setUnitPrice(unitPrice);
        return product;
    }






}
