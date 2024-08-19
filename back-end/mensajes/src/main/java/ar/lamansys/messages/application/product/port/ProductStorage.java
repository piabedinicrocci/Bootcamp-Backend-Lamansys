package ar.lamansys.messages.application.product.port;

import ar.lamansys.messages.domain.product.ProductStoredBo;

import java.util.stream.Stream;

public interface ProductStorage {
    Stream<ProductStoredBo> findAllByUserId(
            String sellerId
    );
}
