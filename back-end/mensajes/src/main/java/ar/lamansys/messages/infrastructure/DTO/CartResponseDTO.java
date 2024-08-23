package ar.lamansys.messages.infrastructure.DTO;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Getter
@NoArgsConstructor
public class CartResponseDTO {
    @Schema(description = "Identificador único del carrito", example = "1")
    private Integer id;
    @Schema(description = "Identificador del usuario asociado con el carrito", example = "user1")
    private String appUserId;
    @Schema(description = "Precio total de los productos en el carrito", example = "1000")
    private Integer totalPrice;
    @Schema(description = "Indica si el carrito ha sido finalizado", example = "false")
    private Boolean isFinalized;
    @Schema(description = "Identificador del vendedor asociado con los productos en el carrito", example = "seller1")
    private String sellerId;
}
