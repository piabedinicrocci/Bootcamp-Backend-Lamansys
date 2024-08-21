package ar.lamansys.messages.application.cartProduct.port;

import ar.lamansys.messages.domain.cartProduct.CartProductBo;


public interface CartProductStorage {
    void createCartProduct(CartProductBo cartProductBo);
    Integer updateQuantity(Integer cartId, Integer productId, Integer newQuantity, Integer quantityPrice);
    CartProductBo findByCartIdAndProductId(Integer cartId, Integer productId);
    Integer calculateTotalPrice(Integer cartId);
    Integer addProductToCart(Integer cartId, Integer productId, Integer quantity, Integer quantityPrice);
    CartProductBo deleteProductFromCart(Integer cartId, Integer productId);
    long  countByCartId(Integer cartId);
}
