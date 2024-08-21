package ar.lamansys.messages.infrastructure.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class ProductResponseDTO {
    private String name;
    private Integer stock;
    private Integer unitPrice;
    private String userId;
}
