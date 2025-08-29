package com.sazzler.ecommerce.ExceptionHandlers;

public class EmailNotFound extends RuntimeException {
    public EmailNotFound(String message) {
        super(message);
    }
}
