package com.paymentcheckservice.exceptions;

public class NotEnoughAmountException extends RuntimeException {
    public NotEnoughAmountException() {
        super("Not enough amount");
    }
}
