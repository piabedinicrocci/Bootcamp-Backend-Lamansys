package ar.lamansys.messages.domain.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NewCartBo {
    private int productId;
    private int quantity;

}
