package ar.lamansys.messages.application.exception;

import lombok.Getter;

@Getter
public class StockHasNotChangedException extends RuntimeException{
    private final Integer productId;
    private final Integer newStock;

    public StockHasNotChangedException(Integer productId, Integer newStock){
        super(String.format("The stock of the product with id %d is already %d",productId,newStock));
        this.productId=productId;
        this.newStock=newStock;
    }
}
