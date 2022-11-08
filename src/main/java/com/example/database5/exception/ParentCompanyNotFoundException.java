package com.example.database5.exception;


public class ParentCompanyNotFoundException extends RuntimeException {
    public ParentCompanyNotFoundException(Integer id) {
        super("Can`t parent company with id: " + id);
    }
}
