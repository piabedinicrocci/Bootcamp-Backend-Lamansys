package ar.lamansys.messages.infrastructure.input.rest.product;

import ar.lamansys.messages.application.exception.UserNotExistsException;
import ar.lamansys.messages.application.product.ListProducts;
import ar.lamansys.messages.domain.product.ProductStoredBo;
import ar.lamansys.messages.infrastructure.DTO.ProductResponseDTO;
import ar.lamansys.messages.infrastructure.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @MockBean
    private ListProducts listProducts;

    @MockBean
    private ProductMapper productMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void productsFromSeller_ok() throws Exception {
        // Arrange
        String userId = "user1";
        List<ProductStoredBo> expectedProducts = List.of(
                new ProductStoredBo("Product1", 40, 600, userId)
        );
        List<ProductResponseDTO> expectedResponse = List.of(
                new ProductResponseDTO("Product1", 40, 600, userId)
        );

        when(listProducts.run(userId)).thenReturn(expectedProducts);
        when(productMapper.boListToProductResponseListDTO(expectedProducts)).thenReturn(expectedResponse);

        // Act y Assert
        mockMvc.perform(get("/products/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(expectedResponse.size()))
                .andExpect(jsonPath("$[0].name").value("Product1"));
    }

    @Test
    void productsFromSeller_throwException() throws Exception {
        // Arrange
        String userId = "invalidUserId";
        when(listProducts.run(userId)).thenThrow(new UserNotExistsException(userId));

        // Act y Assert
        mockMvc.perform(get("/products/{userId}", userId))
                .andExpect(status().isNotFound());
    }
}

