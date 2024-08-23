package ar.lamansys.messages.infrastructure.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "Id del producto con el que se quiere iniciar la compra ", example = "3")
    private Integer productId;
    @NotNull(message = "Quantity can´t be null")
    @Min(value = 1, message = "Quantity must be higher than 0")
    @Schema(description = "Cantidad del producto a agregar al carrito", example = "1")
    private Integer quantity;
}
