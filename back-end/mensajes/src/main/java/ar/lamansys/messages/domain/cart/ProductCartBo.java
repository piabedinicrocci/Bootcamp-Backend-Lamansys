package ar.lamansys.messages.domain.cart;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class ProductCartBo {
    private String productId;
    private String productName;
    private int quantity;
    private String userId;
}
