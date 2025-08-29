package com.sazzler.ecommerce.ExceptionHandlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailNotFound.class)
    ResponseEntity<String> handleEmailNotFoundException(EmailNotFound e){
        return ResponseEntity.internalServerError().body(e.getMessage());
    }
    @ExceptionHandler(ProductNotFound.class)
    ResponseEntity<String> handleProductNotFoundException(ProductNotFound e){
        return ResponseEntity.internalServerError().body(e.getMessage());
    }

}
