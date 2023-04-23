package com.tinkoff.sirius.amalthea.exception;

public class CategoryNotFoundException extends Exception {
    public CategoryNotFoundException() {
    }

    public CategoryNotFoundException(String message) {
        super(message);
    }
}
