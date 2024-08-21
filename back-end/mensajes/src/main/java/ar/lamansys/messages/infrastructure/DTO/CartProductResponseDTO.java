package ar.lamansys.messages.infrastructure.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CartProductResponseDTO {
    private Integer cartId;
    private Integer productId;
    private Integer quantity;
    private Integer quantityPrice;
}
