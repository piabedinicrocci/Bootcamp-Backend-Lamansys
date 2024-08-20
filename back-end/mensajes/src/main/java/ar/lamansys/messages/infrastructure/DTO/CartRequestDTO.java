package ar.lamansys.messages.infrastructure.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartRequestDTO {
    @NotNull(message = "ProductId can´t be null")
    private Integer productId;
    @NotNull(message = "Quantity can´t be null")
    @Min(value = 1, message = "Quantity must be higher than 0")
    private Integer quantity;
}
