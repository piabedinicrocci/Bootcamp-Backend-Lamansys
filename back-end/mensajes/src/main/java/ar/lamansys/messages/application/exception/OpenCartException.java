package ar.lamansys.messages.application.exception;

import lombok.Getter;

@Getter
public class OpenCartException extends Throwable {
    private String idSeller;

    public OpenCartException(String idSeller) {
        super(String.format("A cart already exists between you and the seller %s", idSeller));
        this.idSeller = idSeller;
    }
}
