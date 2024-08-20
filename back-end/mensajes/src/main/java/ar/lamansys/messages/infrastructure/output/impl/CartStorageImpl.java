package ar.lamansys.messages.infrastructure.output.impl;

import ar.lamansys.messages.application.cart.port.CartStorage;
import ar.lamansys.messages.domain.cart.CartStoredBo;
import ar.lamansys.messages.infrastructure.output.entity.Cart;
import ar.lamansys.messages.infrastructure.output.repository.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CartStorageImpl implements CartStorage {
    private final CartRepository cartRepository;

    @Override
    public void createCart(String appUserId, Integer totalPrice, Boolean isFinalized, String sellerId) {
        cartRepository.save(new Cart(appUserId,totalPrice,isFinalized,sellerId));
    }

    @Override
    public CartStoredBo getCartExists(String userId, String idSeller) {
        return cartRepository.getCartExists(userId,idSeller);
    }

}
