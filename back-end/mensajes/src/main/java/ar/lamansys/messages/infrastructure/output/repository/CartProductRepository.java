package ar.lamansys.messages.infrastructure.output.repository;

import ar.lamansys.messages.infrastructure.output.entity.CartProduct;
import ar.lamansys.messages.infrastructure.output.entity.CartProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, CartProductId> {

    @Query("SELECT cp FROM CartProduct cp WHERE cp.id.cartId = :cartId")
    Stream<CartProduct> findAllByCartId(@Param("cartId") Integer cartId);
}
