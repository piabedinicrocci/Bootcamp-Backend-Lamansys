package ar.lamansys.messages.application.exception;

import lombok.Getter;

@Getter
public class ProductFromDiferentSellerException extends RuntimeException{
    private final Integer cartId;
    private final Integer productId;
    public ProductFromDiferentSellerException(Integer cartId, Integer productId){
        super(String.format("The seller of the product with id %d is different from the seller of the cart with id %d",productId,cartId));
        this.cartId=cartId;
        this.productId=productId;

    }
}
