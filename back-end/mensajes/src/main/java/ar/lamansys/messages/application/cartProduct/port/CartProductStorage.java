package ar.lamansys.messages.application.cartProduct.port;

import ar.lamansys.messages.domain.cartProduct.NewCartProductBo;

public interface CartProductStorage {
    void createCartProduct(NewCartProductBo newCartProductBo);
}
