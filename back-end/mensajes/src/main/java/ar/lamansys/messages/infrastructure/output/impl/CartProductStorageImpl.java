package ar.lamansys.messages.infrastructure.output.impl;

import ar.lamansys.messages.application.cartProduct.port.CartProductStorage;
import ar.lamansys.messages.domain.cartProduct.NewCartProductBo;
import ar.lamansys.messages.infrastructure.output.entity.CartProduct;
import ar.lamansys.messages.infrastructure.output.entity.CartProductId;
import ar.lamansys.messages.infrastructure.output.repository.CartProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CartProductStorageImpl implements CartProductStorage {
    private final CartProductRepository cartProductRepository;


    @Override
    public void createCartProduct(NewCartProductBo newCartProductBo) {
        cartProductRepository.save(new CartProduct(new CartProductId(newCartProductBo.getCartId(),newCartProductBo.getProductId()),newCartProductBo.getQuantity(),newCartProductBo.getQuantityPrice()));
    }
    @Override
    public Integer updateQuantity(Integer cartId, Integer productId, Integer newQuantity, Integer quantityPrice){
         return cartProductRepository.updateQuantity(newQuantity, cartId, productId, quantityPrice);
    }

    @Override
    public NewCartProductBo findByCartIdAndProductId(Integer cartId, Integer productId) {
        return cartProductRepository.findByCartIdAndProductId(cartId, productId);
    }
    @Override
    public Integer calculateTotalPrice(Integer cartId) {
        return cartProductRepository.calculateTotalPrice(cartId);
    }

    @Override
    public Integer addProductToCart(Integer cartId, Integer productId, Integer quantity, Integer quantityPrice) {
        //o usar save
        return cartProductRepository.addProductToCart(cartId, productId, quantity, quantityPrice);
    }

}
