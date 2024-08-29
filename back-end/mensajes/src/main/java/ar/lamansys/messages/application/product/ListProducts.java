package ar.lamansys.messages.application.product;

import ar.lamansys.messages.application.product.port.ProductStorage;
import ar.lamansys.messages.application.user.AssertUserExists;
import ar.lamansys.messages.application.exception.UserNotExistsException;
import ar.lamansys.messages.domain.product.ProductStoredBo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ListProducts {
    private final ProductStorage productStorage;
    private final AssertUserExists assertUserExists;

    @Transactional(readOnly = true)
    public List<ProductStoredBo> run(String userId) throws UserNotExistsException {
        assertUserExists.run(userId);
        List<ProductStoredBo> products= productStorage.findAllByUserId(userId).collect(Collectors.toList());
        return products;
    }

    @Transactional(readOnly = true)
    public List<ProductStoredBo> run() {
        List<ProductStoredBo> products= productStorage.findAllProducts().collect(Collectors.toList());
        return products;
    }


}
