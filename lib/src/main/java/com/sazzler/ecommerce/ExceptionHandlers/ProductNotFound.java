package com.sazzler.ecommerce.ExceptionHandlers;

public class ProductNotFound extends RuntimeException {
    public ProductNotFound(String message) {
        super(message);
    }
}
