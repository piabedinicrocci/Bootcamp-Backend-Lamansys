package ar.lamansys.messages.infrastructure.mapper;
import ar.lamansys.messages.domain.cart.CartStoredBo;
import ar.lamansys.messages.domain.cart.NewCartBo;
import ar.lamansys.messages.infrastructure.DTO.CartRequestDTO;
import ar.lamansys.messages.infrastructure.DTO.CartResponseDTO;
import ar.lamansys.messages.infrastructure.output.entity.Cart;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {

    public CartMapper() {
    }

    public NewCartBo toNewCartBo(CartRequestDTO request) {
        return new NewCartBo(request.getProductId(), request.getQuantity());
    }

    public CartStoredBo entityToCartStoredBo(Cart cart) {
        return new CartStoredBo(cart.getId(), cart.getAppUserId(), cart.getTotalPrice(), cart.getIsFinalized(), cart.getSellerId());
    }

    public CartResponseDTO cartStoredBoToCartResponseDTO(CartStoredBo cartStoredBo) {
        return new CartResponseDTO(cartStoredBo.getId(), cartStoredBo.getAppUserId(), cartStoredBo.getTotalPrice(), cartStoredBo.getIsFinalized(), cartStoredBo.getSellerId());
    }

}
