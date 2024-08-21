package ar.lamansys.messages.infrastructure.mapper;
import ar.lamansys.messages.domain.product.ProductStoredBo;
import ar.lamansys.messages.infrastructure.DTO.ProductResponseDTO;
import ar.lamansys.messages.infrastructure.DTO.CartResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public ProductMapper() {
    }

    public ProductResponseDTO productStoredBoToProductResponseDTO(ProductStoredBo productStoredBo) {
        return new ProductResponseDTO(productStoredBo.getName(), productStoredBo.getStock(), productStoredBo.getUnitPrice(), productStoredBo.getUserId());
    }

    public List<ProductResponseDTO> boListToProductResponseListDTO(List<ProductStoredBo> boList) {
        return boList.stream()
                .map(this::productStoredBoToProductResponseDTO)
                .collect(Collectors.toList());
    }

}
