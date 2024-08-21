package ar.lamansys.messages.application.exception;

import lombok.Getter;

@Getter
public class ProductInCartException extends RuntimeException{
    private final Integer cartId;
    private final Integer productId;
    public ProductInCartException(Integer cartId, Integer productId){
        super(String.format("Product with id %d is not in cart %d",productId,cartId));
        this.cartId=cartId;
        this.productId=productId;

    }
}
