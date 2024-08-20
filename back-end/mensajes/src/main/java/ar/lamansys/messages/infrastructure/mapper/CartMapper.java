package ar.lamansys.messages.infrastructure.mapper;
import ar.lamansys.messages.domain.cart.NewCartBo;
import ar.lamansys.messages.infrastructure.DTO.CartRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {

    public CartMapper() {
    }

    public NewCartBo toNewCartBo(CartRequestDTO request) {
        return new NewCartBo(request.getProductId(), request.getQuantity());
    }

}
