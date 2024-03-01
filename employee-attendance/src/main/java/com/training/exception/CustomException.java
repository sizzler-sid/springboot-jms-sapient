package com.training.exception;

public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CustomException(String message,Exception e) {
        super(message,e);
    }

}
