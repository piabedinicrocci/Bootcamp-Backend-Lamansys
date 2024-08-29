package ar.lamansys.messages.application.exception;

import lombok.Getter;

@Getter
public class UnitPriceHasNotChangedException extends RuntimeException{
    private final Integer productId;
    private final Integer newPrice;

    public UnitPriceHasNotChangedException(Integer productId, Integer newPrice){
        super(String.format("The price of the product with id %d is already %d",productId,newPrice));
        this.productId=productId;
        this.newPrice=newPrice;
    }
}
