package ar.lamansys.messages.domain.cartProduct;

import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public class CartProductBo {
    private Integer cartId;
    private Integer productId;
    private Integer quantity;
    private Integer quantityPrice;
}
