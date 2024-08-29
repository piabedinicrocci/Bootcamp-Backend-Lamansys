package ar.lamansys.messages.infrastructure.input.rest.product;

import ar.lamansys.messages.application.product.UpdateStock;
import ar.lamansys.messages.application.exception.UserNotExistsException;
import ar.lamansys.messages.application.product.ListProducts;
import ar.lamansys.messages.application.product.UpdateUnitPrice;
import ar.lamansys.messages.domain.product.ProductStoredBo;
import ar.lamansys.messages.infrastructure.DTO.ProductResponseDTO;
import ar.lamansys.messages.infrastructure.DTO.StockDTO;
import ar.lamansys.messages.infrastructure.DTO.UnitPriceDTO;
import ar.lamansys.messages.infrastructure.mapper.ProductMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name="Product",  description = "Operaciones relacionadas con los productos")
@RequestMapping("/products")
public class ProductController {
    private final ListProducts listProducts;
    private final ProductMapper productMapper;
    private final UpdateStock updateStock;
    private final UpdateUnitPrice updateUnitPrice;

    @GetMapping("/{userId}")
    @Operation(summary = "Obtiene productos de un vendedor",
            description = "Este endpoint se utiliza para obtener los productos vendidos por el usuario especificado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Productos del vendedor obtenidas con exito"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
    })
    public ResponseEntity<List<ProductResponseDTO>> productsFromSeller(@Parameter(description = "ID del usuario vendedor", required = true)@PathVariable String userId) throws UserNotExistsException {
        List<ProductStoredBo> products = listProducts.run(userId);
        List<ProductResponseDTO> response = productMapper.boListToProductResponseListDTO(products);
        return ResponseEntity.status(200).body(response);
    }

    @GetMapping()
    @Operation(summary = "Obtiene todos los productos independientemente del vendedor",
            description = "Este endpoint se utiliza para obtener los productos vendidos por todos los usuarios.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Productos del vendedor obtenidas con exito"),
    })
    public ResponseEntity<List<ProductResponseDTO>> getProducts() {
        List<ProductStoredBo> products = listProducts.run();
        List<ProductResponseDTO> response = productMapper.boListToProductResponseListDTO(products);
        return ResponseEntity.status(200).body(response);
    }

    @PutMapping("/{productId}/stock")
    @Operation(summary = "Actualizar stock",
            description = "Este endpoint se utiliza para cambiar el stock de un producto del usuario especificado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Stock actualizado con éxito"),
            @ApiResponse(responseCode = "400", description = "El producto no pertenece al usuario"),
            @ApiResponse(responseCode = "404", description = "Usuario o producto no encontrado"),
    })
    public ResponseEntity<String> updateStock(@Parameter(description = "ID del usuario", in = ParameterIn.HEADER, required = true )@RequestHeader String userId, @Parameter(description = "ID del producto", required = true) @PathVariable Integer productId, @Parameter(description = "DTO con el nuevo stock", required = true) @Valid @RequestBody StockDTO stock) throws UserNotExistsException {
        updateStock.run(userId, productId, stock.getStock());
        return ResponseEntity.ok("The stock of the product was succesfully changed");
    }

    @PutMapping("/{productId}/price")
    @Operation(summary = "Actualizar precio",
            description = "Este endpoint se utiliza para cambiar el precio de un producto del usuario especificado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Precio actualizado con éxito"),
            @ApiResponse(responseCode = "400", description = "El producto no pertenece al usuario"),
            @ApiResponse(responseCode = "404", description = "Usuario o producto no encontrado"),
    })
    public ResponseEntity<String> updateUnitPrice(@Parameter(description = "ID del usuario", in = ParameterIn.HEADER, required = true )@RequestHeader String userId, @Parameter(description = "ID del producto", required = true) @PathVariable Integer productId, @Parameter(description = "DTO con el nuevo precio", required = true) @Valid @RequestBody UnitPriceDTO price) throws UserNotExistsException {
        updateUnitPrice.run(userId, productId, price.getUnitPrice());
        return ResponseEntity.ok("The price of the product was succesfully changed");
    }

}
