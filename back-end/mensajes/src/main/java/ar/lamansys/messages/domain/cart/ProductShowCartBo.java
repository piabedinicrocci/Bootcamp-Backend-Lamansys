package ar.lamansys.messages.domain.cart;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class ProductShowCartBo {
    private Integer productId;
    private String name;
    private Integer unitPrice;
    private Integer quantity;
    private Integer quantityPrice;
}
