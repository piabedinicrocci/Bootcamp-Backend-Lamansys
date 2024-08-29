package ar.lamansys.messages.infrastructure.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UnitPriceDTO {
    @NotNull
    @Positive
    @Schema(description = "Nuevo precio unitario del producto", example = "1000")
    private Integer unitPrice;
}
