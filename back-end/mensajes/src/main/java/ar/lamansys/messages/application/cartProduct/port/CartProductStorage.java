package ar.lamansys.messages.application.cartProduct.port;

import ar.lamansys.messages.domain.cartProduct.NewCartProductBo;


public interface CartProductStorage {
    void createCartProduct(NewCartProductBo newCartProductBo);
    Integer updateQuantity(Integer cartId, Integer productId, Integer newQuantity, Integer quantityPrice);
    NewCartProductBo findByCartIdAndProductId(Integer cartId, Integer productId);
    Integer calculateTotalPrice(Integer cartId);
    Integer addProductToCart(Integer cartId, Integer productId, Integer quantity, Integer quantityPrice);
}
