package com.saida.bank_api.exception;

public class SameAccountTransferException extends RuntimeException {
    public SameAccountTransferException(String message) {
        super(message);
    }

    public SameAccountTransferException(String message, Throwable cause) {
        super(message, cause);
    }
}
