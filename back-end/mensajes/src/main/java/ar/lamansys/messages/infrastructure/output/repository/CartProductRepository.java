package ar.lamansys.messages.infrastructure.output.repository;

import ar.lamansys.messages.domain.cartProduct.NewCartProductBo;
import ar.lamansys.messages.infrastructure.output.entity.CartProduct;
import ar.lamansys.messages.infrastructure.output.entity.CartProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, CartProductId> {

    @Query("SELECT cp FROM CartProduct cp WHERE cp.id.cartId = :cartId")
    Stream<CartProduct> findAllByCartId(@Param("cartId") Integer cartId);

    @Modifying
    @Query("UPDATE CartProduct cp SET cp.quantity = :quantity, cp.quantityPrice = :quantityPrice WHERE cp.id.cartId = :cartId AND cp.id.productId = :productId")
    Integer updateQuantity(@Param("quantity") Integer quantity, @Param("cartId") Integer cartId, @Param("productId") Integer productId, @Param("quantityPrice") Integer quantityPrice);

    @Query("SELECT NEW ar.lamansys.messages.domain.cartProduct.NewCartProductBo(cp.id.cartId, cp.id.productId,cp.quantity, cp.quantityPrice) FROM CartProduct cp WHERE cp.id.cartId = :cartId AND cp.id.productId = :productId")
    NewCartProductBo findByCartIdAndProductId(@Param("cartId") Integer cartId, @Param("productId") Integer productId);

    @Query("SELECT sum(cp.quantityPrice) FROM CartProduct cp WHERE cp.id.cartId = :cartId")
    Integer calculateTotalPrice(@Param("cartId") Integer cartId);

    @Modifying
    @Query(value = "INSERT INTO cart_product (cart_id, product_id, quantity, quantity_price) VALUES (:cartId, :productId, :quantity, :quantityPrice)", nativeQuery = true)
    Integer addProductToCart(@Param("cartId") Integer cartId, @Param("productId") Integer productId, @Param("quantity") Integer quantity, @Param("quantityPrice") Integer quantityPrice);
}
