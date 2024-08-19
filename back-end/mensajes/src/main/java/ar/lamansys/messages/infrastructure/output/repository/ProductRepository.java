package ar.lamansys.messages.infrastructure.output.repository;

import ar.lamansys.messages.domain.product.ProductStoredBo;
import ar.lamansys.messages.infrastructure.output.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT NEW ar.lamansys.messages.domain.product.ProductStoredBo(p.name, p.stock, p.unitPrice) " +
            "FROM Product p " +
            "WHERE p.userId = :userId " )
    Stream<ProductStoredBo> findAllByUserId(@Param("userId")String userId);

}
