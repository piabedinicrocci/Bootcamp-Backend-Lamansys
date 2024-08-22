package ar.lamansys.messages.application.exception;

import lombok.Getter;

@Getter
public class CartIsFinalizedException extends RuntimeException{
    private final Integer cartId;

    public CartIsFinalizedException(Integer cartId){
        super(String.format("The cart with id %d is being processed, you cannot modify it",cartId));
        this.cartId=cartId;
    }
}
