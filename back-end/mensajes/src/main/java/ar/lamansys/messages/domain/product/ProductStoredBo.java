package ar.lamansys.messages.domain.product;

import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public class ProductStoredBo {
    private String name;
    private Integer stock;
    private Integer unitPrice;
    private String userId;
}
