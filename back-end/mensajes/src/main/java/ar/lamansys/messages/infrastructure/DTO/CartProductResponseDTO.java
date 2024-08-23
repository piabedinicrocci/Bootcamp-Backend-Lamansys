package ar.lamansys.messages.infrastructure.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CartProductResponseDTO {
    @Schema(description = "ID del carrito de compras", example = "1")
    private Integer cartId;
    @Schema(description = "ID del producto dentro del carrito", example = "3")
    private Integer productId;
    @Schema(description = "Cantidad de unidades del producto en el carrito", example = "2")
    private Integer quantity;
    @Schema(description = "Precio total por la cantidad de producto en el carrito", example = "2400")
    private Integer quantityPrice;
}
