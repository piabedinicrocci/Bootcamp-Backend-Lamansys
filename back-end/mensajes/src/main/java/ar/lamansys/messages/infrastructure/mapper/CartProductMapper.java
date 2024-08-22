package ar.lamansys.messages.infrastructure.mapper;

import ar.lamansys.messages.domain.cart.CartSummaryBo;
import ar.lamansys.messages.domain.cart.ProductFromCartBo;
import ar.lamansys.messages.domain.cart.ProductShowCartBo;
import ar.lamansys.messages.domain.cartProduct.CartProductBo;
import ar.lamansys.messages.domain.product.ProductStoredBo;
import ar.lamansys.messages.infrastructure.DTO.CartProductResponseDTO;
import ar.lamansys.messages.infrastructure.DTO.CartSummaryDTO;
import ar.lamansys.messages.infrastructure.DTO.ProductResponseDTO;
import ar.lamansys.messages.infrastructure.DTO.ProductShowCartDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartProductMapper {
    public CartProductMapper(){}

    public CartProductResponseDTO cartProductBoToCartProductDTO(CartProductBo bo) {
        return new CartProductResponseDTO(bo.getCartId(), bo.getProductId(), bo.getQuantity(), bo.getQuantityPrice());
    }

    public CartSummaryDTO cartSummaryBoToCartSummaryDTO(CartSummaryBo bo) {
        List<ProductShowCartDTO> dto= this.boListToProductShowCartListDTO(bo.getProducts());
        return new CartSummaryDTO(dto,bo.getTotalPrice());
    }

    public List<ProductShowCartDTO> boListToProductShowCartListDTO(List<ProductShowCartBo> boList) {
        return boList.stream()
                .map(this::productShowCartBoToProductShowCartDTO)
                .collect(Collectors.toList());
    }

    public ProductShowCartDTO productShowCartBoToProductShowCartDTO(ProductShowCartBo bo) {
        return new ProductShowCartDTO(bo.getProductId(), bo.getName(), bo.getUnitPrice(), bo.getQuantity(), bo.getQuantityPrice());
    }

}
