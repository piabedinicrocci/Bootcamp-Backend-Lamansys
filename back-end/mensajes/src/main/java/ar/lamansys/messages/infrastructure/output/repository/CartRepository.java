package ar.lamansys.messages.infrastructure.output.repository;

import ar.lamansys.messages.domain.cart.CartStoredBo;
import ar.lamansys.messages.infrastructure.output.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Query("SELECT c FROM Cart c WHERE c.appUserId = :appUserId AND c.sellerId = :sellerId")
    Optional<Cart> findByAppUserIdAndSellerId(@Param("appUserId") String appUserId, @Param("sellerId") String sellerId);

    @Query("SELECT NEW ar.lamansys.messages.domain.cart.CartStoredBo(c.id,c.appUserId, c.totalPrice, c.isFinalized, c.sellerId) " +
            "FROM Cart c " +
            "WHERE c.appUserId = :userId AND c.sellerId = :sellerId AND c.isFinalized = false")
    CartStoredBo getCartExists(@Param("userId") String userId, @Param("sellerId") String idSeller);

    Optional<Cart> findByIdAndAppUserId(Integer id, String appUserId);
    @Modifying
    @Query("UPDATE Cart c SET c.totalPrice=:newTotalPrice WHERE c.id=:cartId")
    void updateTotalPrice(@Param("cartId")Integer cartId, @Param("newTotalPrice") Integer newTotalPrice);

    @Query("SELECT c.totalPrice FROM Cart c WHERE c.id = :cartId")
    Integer getTotalPrice(@Param("cartId")Integer cartId);

    @Query("SELECT c.sellerId " +
            "FROM Cart c " +
            "WHERE c.id=:cartId")
    String getSellerById(@Param("cartId") Integer cartId);

    @Query("SELECT c.isFinalized FROM Cart c WHERE c.id=:cartId")
    boolean getIsFinalizedById(@Param("cartId") Integer cartId);
    @Modifying
    @Query("UPDATE Cart c SET c.isFinalized=true")
    void updateIsFinalized(Integer cartId);
}
