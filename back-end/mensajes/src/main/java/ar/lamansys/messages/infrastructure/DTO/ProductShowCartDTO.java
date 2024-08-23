package ar.lamansys.messages.infrastructure.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductShowCartDTO {
    @Schema(description = "ID del producto en el carrito", example = "3")
    private Integer productId;
    @Schema(description = "Nombre del producto", example = "Smartphone")
    private String name;
    @Schema(description = "Precio unitario del producto", example = "500")
    private Integer unitPrice;
    @Schema(description = "Cantidad del producto en el carrito", example = "2")
    private Integer quantity;
    @Schema(description = "Precio total por la cantidad de productos", example = "1000")
    private Integer quantityPrice;
}
