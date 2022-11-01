package com.example.database5.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(Integer id) {
        super("Could not find 'category' with id = " + id);
    }

}
