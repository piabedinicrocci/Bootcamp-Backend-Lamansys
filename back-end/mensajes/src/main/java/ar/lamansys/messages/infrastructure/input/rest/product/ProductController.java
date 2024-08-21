package ar.lamansys.messages.infrastructure.input.rest.product;

import ar.lamansys.messages.application.exception.UserNotExistsException;
import ar.lamansys.messages.application.product.ListProducts;
import ar.lamansys.messages.domain.product.ProductStoredBo;
import ar.lamansys.messages.infrastructure.DTO.CartResponseDTO;
import ar.lamansys.messages.infrastructure.DTO.ProductResponseDTO;
import ar.lamansys.messages.infrastructure.mapper.CartMapper;
import ar.lamansys.messages.infrastructure.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ListProducts listProducts;
    private final ProductMapper productMapper;

    @GetMapping("/{userId}")
    public ResponseEntity<List<ProductResponseDTO>> productsFromSeller(@PathVariable String userId) throws UserNotExistsException {
        List<ProductStoredBo> products = listProducts.run(userId);
        List<ProductResponseDTO> response = productMapper.boListToProductResponseListDTO(products);
        return ResponseEntity.status(201).body(response);
    }

}
