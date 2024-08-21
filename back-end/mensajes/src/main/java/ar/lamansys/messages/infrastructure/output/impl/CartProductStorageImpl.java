package ar.lamansys.messages.infrastructure.output.impl;

import ar.lamansys.messages.application.cartProduct.port.CartProductStorage;
import ar.lamansys.messages.domain.cartProduct.CartProductBo;
import ar.lamansys.messages.infrastructure.output.entity.CartProduct;
import ar.lamansys.messages.infrastructure.output.entity.CartProductId;
import ar.lamansys.messages.infrastructure.output.repository.CartProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CartProductStorageImpl implements CartProductStorage {
    private final CartProductRepository cartProductRepository;


    @Override
    public void createCartProduct(CartProductBo cartProductBo) {
        cartProductRepository.save(new CartProduct(new CartProductId(cartProductBo.getCartId(), cartProductBo.getProductId()), cartProductBo.getQuantity(), cartProductBo.getQuantityPrice()));
    }
    @Override
    public Integer updateQuantity(Integer cartId, Integer productId, Integer newQuantity, Integer quantityPrice){
         return cartProductRepository.updateQuantity(newQuantity, cartId, productId, quantityPrice);
    }

    @Override
    public CartProductBo findByCartIdAndProductId(Integer cartId, Integer productId) {
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
    @Override
    public CartProductBo deleteProductFromCart(Integer cartId, Integer productId) {
        CartProductBo productDeletedFromCart=this.findByCartIdAndProductId(cartId,productId);
        cartProductRepository.deleteProductFromCart(cartId,productId);
        return productDeletedFromCart;
    }
    @Override
    public long  countByCartId(Integer cartId){
        return cartProductRepository.countByCartId(cartId);
    }

}
