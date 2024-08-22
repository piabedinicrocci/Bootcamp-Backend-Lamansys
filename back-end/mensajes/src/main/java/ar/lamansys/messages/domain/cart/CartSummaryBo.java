package ar.lamansys.messages.domain.cart;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor
public class CartSummaryBo {
    private List<ProductShowCartBo> products;
    private Integer totalPrice;
}

