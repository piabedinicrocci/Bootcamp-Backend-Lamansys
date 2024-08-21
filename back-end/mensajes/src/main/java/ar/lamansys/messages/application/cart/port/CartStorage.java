package ar.lamansys.messages.application.cart.port;

import ar.lamansys.messages.domain.cart.CartStoredBo;

import java.util.Optional;

public interface CartStorage {

    CartStoredBo createCart(String userId, Integer totalPrice, Boolean is_finalized, String sellerId);

    CartStoredBo getCartExists(String userId, String idSeller);

    Optional<CartStoredBo> findByIdAndAppUserId(Integer id, String appUserId);


    void updateTotalPrice(Integer cartId, Integer newTotalPrice);

}
