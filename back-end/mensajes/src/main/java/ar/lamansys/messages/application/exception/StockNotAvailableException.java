package ar.lamansys.messages.application.exception;

import lombok.Getter;

@Getter
public class StockNotAvailableException extends RuntimeException {
    private Integer productId;
    private Integer quantity;

    public StockNotAvailableException(Integer productId, Integer quantity, Integer missing) {
        super(String.format("There is not enough stock to supply the quantity of %s for the product with id %s. %d quantities are missing", quantity, productId, missing));
        this.productId = productId;
        this.quantity = quantity;
    }
}
