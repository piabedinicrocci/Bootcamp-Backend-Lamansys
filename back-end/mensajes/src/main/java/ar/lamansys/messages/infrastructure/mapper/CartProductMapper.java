package ar.lamansys.messages.infrastructure.mapper;

import ar.lamansys.messages.domain.cartProduct.CartProductBo;
import ar.lamansys.messages.infrastructure.DTO.CartProductResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class CartProductMapper {
    public CartProductMapper(){}

    public CartProductResponseDTO cartProductBoToCartProductDTO(CartProductBo bo) {
        return new CartProductResponseDTO(bo.getCartId(), bo.getProductId(), bo.getQuantity(), bo.getQuantityPrice());
    }
}
