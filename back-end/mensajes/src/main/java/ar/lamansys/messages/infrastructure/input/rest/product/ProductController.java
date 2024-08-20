package ar.lamansys.messages.infrastructure.input.rest.product;

import ar.lamansys.messages.application.exception.UserNotExistsException;
import ar.lamansys.messages.application.product.ListProducts;
import ar.lamansys.messages.domain.product.ProductStoredBo;
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

    /**
     * Obtiene los productos de un vendedor específico.
     *
     * @param userId el ID del usuario (vendedor) cuyos productos se desean obtener
     * @return un Stream de objetos {@link ProductStoredBo} que representan los productos del vendedor
     * @throws UserNotExistsException si el usuario especificado no existe
     */
    @GetMapping("/{userId}")
    public ResponseEntity<List<ProductStoredBo>> productsFromSeller(@PathVariable String userId) throws UserNotExistsException {
        List<ProductStoredBo> products = listProducts.run(userId);
        return ResponseEntity.ok(products);
    }

}
