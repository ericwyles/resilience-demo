package com.ericwyles.resiliencedemo.exception;

public class UnreliableServiceException extends RuntimeException {
    public UnreliableServiceException() {
        super();
    }

    public UnreliableServiceException(String message) {
        super(message);
    }
}
