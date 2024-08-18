package ar.lamansys.messages.infrastructure.output.repository;

import ar.lamansys.messages.infrastructure.output.entity.CartProduct;
import ar.lamansys.messages.infrastructure.output.entity.CartProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, CartProductId> {

}
