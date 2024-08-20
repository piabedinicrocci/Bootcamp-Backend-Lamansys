package ar.lamansys.messages.application.cart.port;

import ar.lamansys.messages.domain.cart.CartStoredBo;

public interface CartStorage {
    void createCart(String userId, Integer totalPrice, Boolean is_finalized, String sellerId);

    CartStoredBo getCartExists(String userId, String idSeller);
}
