package ar.lamansys.messages.application.exception;

import lombok.Getter;

@Getter
public class ProductIsNotFromSellerException extends RuntimeException{
    private final Integer productId;
    private final String appUserId;

    public ProductIsNotFromSellerException(Integer productId, String appUserId){
        super(String.format("The product with id %d is not from the seller with id %s",productId,appUserId));
        this.productId=productId;
        this.appUserId=appUserId;
    }
}
