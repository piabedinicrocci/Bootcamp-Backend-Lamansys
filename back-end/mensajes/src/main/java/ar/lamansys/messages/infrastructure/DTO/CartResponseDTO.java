package ar.lamansys.messages.infrastructure.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class CartResponseDTO {
    private Integer id;
    private String appUserId;
    private Integer totalPrice;
    private Boolean isFinalized;
    private String sellerId;
}
