package ar.lamansys.messages.application.cartProduct.port;

import ar.lamansys.messages.domain.cartProduct.NewCartProductBo;


public interface CartProductStorage {
    void createCartProduct(NewCartProductBo newCartProductBo);
    int updateQuantity(Integer cartId, Integer productId, Integer newQuantity);
    NewCartProductBo findByCartIdAndProductId(Integer cartId, Integer productId);
}
