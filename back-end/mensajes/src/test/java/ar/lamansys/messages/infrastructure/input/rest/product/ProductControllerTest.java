package ar.lamansys.messages.infrastructure.input.rest.product;

import ar.lamansys.messages.application.exception.ProductIsNotFromSellerException;
import ar.lamansys.messages.application.exception.UserNotExistsException;
import ar.lamansys.messages.application.product.ListProducts;
import ar.lamansys.messages.application.product.UpdateStock;
import ar.lamansys.messages.application.product.UpdateUnitPrice;
import ar.lamansys.messages.domain.product.ProductStoredBo;
import ar.lamansys.messages.infrastructure.DTO.ProductResponseDTO;
import ar.lamansys.messages.infrastructure.DTO.StockDTO;
import ar.lamansys.messages.infrastructure.DTO.UnitPriceDTO;
import ar.lamansys.messages.infrastructure.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @MockBean
    private ListProducts listProducts;

    @MockBean
    private UpdateStock updateStock;

    @MockBean
    private UpdateUnitPrice updateUnitPrice;

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

    @Test
    void updateStock_ok() throws Exception {
        // Arrange
        String userId = "user1";
        Integer productId = 1;
        StockDTO stockDTO = new StockDTO(50);

        doNothing().when(updateStock).run(userId, productId, stockDTO.getStock());

        // Act y Assert
        mockMvc.perform(put("/products/{productId}/stock", productId)
                        .header("userId", userId)
                        .contentType(APPLICATION_JSON)
                        .content("{\"stock\": 50}"))
                .andExpect(status().isOk())
                .andExpect(content().string("The stock of the product was succesfully changed"));
    }

    @Test
    void updateStock_productNotFromSeller() throws Exception {
        // Arrange
        String userId = "user2";
        Integer productId = 1;
        StockDTO stockDTO = new StockDTO(50);

        doThrow(new ProductIsNotFromSellerException(productId, userId)).when(updateStock).run(userId, productId, stockDTO.getStock());

        // Act y Assert
        mockMvc.perform(put("/products/{productId}/stock", productId)
                        .header("userId", userId)
                        .contentType(APPLICATION_JSON)
                        .content("{\"stock\": 50}"))
                .andExpect(status().isForbidden());
    }

    @Test
    void updateUnitPrice_ok() throws Exception {
        // Arrange
        String userId = "user1";
        Integer productId = 1;
        UnitPriceDTO priceDTO = new UnitPriceDTO(1000);

        doNothing().when(updateUnitPrice).run(userId, productId, priceDTO.getUnitPrice());

        // Act y Assert
        mockMvc.perform(put("/products/{productId}/price", productId)
                        .header("userId", userId)
                        .contentType(APPLICATION_JSON)
                        .content("{\"unitPrice\": 1000}"))
                .andExpect(status().isOk())
                .andExpect(content().string("The price of the product was succesfully changed"));
    }

    @Test
    void updateUnitPrice_productNotFromSeller() throws Exception {
        // Arrange
        String userId = "user2";
        Integer productId = 1;
        UnitPriceDTO priceDTO = new UnitPriceDTO(1000);

        doThrow(new ProductIsNotFromSellerException(productId, userId)).when(updateUnitPrice).run(userId, productId, priceDTO.getUnitPrice());

        // Act y Assert
        mockMvc.perform(put("/products/{productId}/price", productId)
                        .header("userId", userId)
                        .contentType(APPLICATION_JSON)
                        .content("{\"unitPrice\": 1000}"))
                .andExpect(status().isForbidden());
    }


}

