package ar.lamansys.messages.infrastructure.output.impl;

import ar.lamansys.messages.application.cart.port.CartStorage;
import ar.lamansys.messages.domain.cart.CartStoredBo;
import ar.lamansys.messages.infrastructure.mapper.CartMapper;
import ar.lamansys.messages.infrastructure.output.entity.Cart;
import ar.lamansys.messages.infrastructure.output.repository.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CartStorageImpl implements CartStorage {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    @Override
    public CartStoredBo createCart(String appUserId, Integer totalPrice, Boolean isFinalized, String sellerId) {
        Cart cart = cartRepository.save(new Cart(appUserId, totalPrice, isFinalized, sellerId));
        return cartMapper.entityToCartStoredBo(cart);
    }

    @Override
    public CartStoredBo getCartExists(String userId, String idSeller) {
        return cartRepository.getCartExists(userId, idSeller);
    }

    @Override
    public Optional<CartStoredBo> findByIdAndAppUserId(Integer cartId, String appUserId) {
        return cartRepository.findByIdAndAppUserId(cartId, appUserId)
                .map(cartMapper::entityToCartStoredBo);
    }

    @Override
    public void updateTotalPrice(Integer cartId, Integer newTotalPrice) {
        cartRepository.updateTotalPrice(cartId, newTotalPrice);
    }

    @Override
    public void deleteCartById(Integer cartId) {
        cartRepository.deleteById(cartId);
    }

    @Override

    public Integer getTotalPrice(Integer cartId) {
        return cartRepository.getTotalPrice(cartId);
    }
    @Override
    public String getSellerById(Integer cartId){
        return cartRepository.getSellerById(cartId);
    }
    @Override
    public boolean getIsFinalizedById (Integer cartId){
        return cartRepository.getIsFinalizedById(cartId);
    }

    @Override
    public void updateIsFinalized(Integer cartId) {
        cartRepository.updateIsFinalized(cartId);
    }

}
