package ar.lamansys.messages.application.product.port;

import ar.lamansys.messages.domain.product.ProductStoredBo;

import java.util.stream.Stream;
import java.util.Optional;

public interface ProductStorage {
    Stream<ProductStoredBo> findAllByUserId(String sellerId);

    boolean exists(Integer productId);

    Integer getStock(Integer productId);

    String getSellerByProductId(Integer productId);

    Integer getUnitPrice(Integer productId);

    Integer findPriceByProductId(Integer productId);

    void updateStock(Integer productId, Integer newStock);

    Stream<ProductStoredBo> findAllProducts();

    void updateUnitPrice(Integer productId, Integer newPrice);

}
