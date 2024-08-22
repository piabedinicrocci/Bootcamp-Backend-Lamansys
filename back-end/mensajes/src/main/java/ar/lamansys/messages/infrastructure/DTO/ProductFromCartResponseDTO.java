package ar.lamansys.messages.infrastructure.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class ProductFromCartResponseDTO {
    private String name;
    private Integer unitPrice;
    private Integer quantity;
    private Integer quantityPrice;
}
