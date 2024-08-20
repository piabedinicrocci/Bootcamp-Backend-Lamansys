package ar.lamansys.messages.application.exception;

import lombok.Getter;

@Getter
public class ProductNotExistsException extends RuntimeException {
    private Integer productId;

    public ProductNotExistsException(Integer productId) {
        super(String.format("Product %s doesn't exist", productId));
        this.productId = productId;
    }
}
