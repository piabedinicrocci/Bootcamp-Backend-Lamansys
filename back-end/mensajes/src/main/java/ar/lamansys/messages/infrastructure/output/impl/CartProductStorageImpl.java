package ar.lamansys.messages.infrastructure.output.impl;

import ar.lamansys.messages.application.cartProduct.port.CartProductStorage;
import ar.lamansys.messages.domain.cartProduct.NewCartProductBo;
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
    public void createCartProduct(NewCartProductBo newCartProductBo) {
        cartProductRepository.save(new CartProduct(new CartProductId(newCartProductBo.getCartId(),newCartProductBo.getProductId()),newCartProductBo.getQuantity(),newCartProductBo.getQuantityPrice()));
    }

}
