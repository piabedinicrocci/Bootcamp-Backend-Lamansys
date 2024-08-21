package ar.lamansys.messages.application.exception;

import lombok.Getter;

@Getter
public class CartUserNotExistsException extends RuntimeException{
    private final Integer cartId;
    private final String appUserId;
    public CartUserNotExistsException(Integer cartId, String appUserId){
        super(String.format("A cart with ID %d doesn´t exists for user with id %s", cartId, appUserId));
        this.cartId=cartId;
        this.appUserId=appUserId;

    }
}
