package ar.lamansys.messages.infrastructure.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductShowCartDTO {
    private Integer productId;
    private String name;
    private Integer unitPrice;
    private Integer quantity;
    private Integer quantityPrice;
}
