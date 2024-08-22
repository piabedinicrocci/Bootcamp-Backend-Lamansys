package ar.lamansys.messages.application.exception;

import lombok.Getter;

@Getter
public class ProductPriceChangedException extends RuntimeException {
    private final Integer productId;
    private final Integer oldPrice;
    private final Integer newPrice;
    public ProductPriceChangedException(Integer productId, Integer oldPrice, Integer newPrice) {
        super(String.format("The price of product with id %d has changed from %d to %d", productId, oldPrice, newPrice));
        this.productId = productId;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
    }
}