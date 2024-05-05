package com.webapp.foodgate.exception;

public class UnauthorizationException extends RuntimeException {
    public UnauthorizationException(String message){
        super(message);
    }
}
