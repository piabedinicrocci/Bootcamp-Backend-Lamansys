package ar.lamansys.messages.infrastructure.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class ProductResponseDTO {
    @Schema(description = "Nombre del producto", example = "Producto 1")
    private String name;
    @Schema(description = "Cantidad disponible en stock", example = "15")
    private Integer stock;
    @Schema(description = "Precio unitario del producto", example = "1200")
    private Integer unitPrice;
    @Schema(description = "ID del usuario que vende el producto", example = "user3")
    private String userId;
}
