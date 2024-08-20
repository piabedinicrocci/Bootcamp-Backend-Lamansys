package ar.lamansys.messages.domain.cart;

import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public class CartStoredBo {
    private Integer id;
    private String appUserId;
    private Integer totalPrice;
    private Boolean isFinalized;
    private String sellerId;

}
