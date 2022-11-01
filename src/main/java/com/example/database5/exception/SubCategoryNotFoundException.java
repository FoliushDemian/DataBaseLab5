
package com.example.database5.exception;

public class SubCategoryNotFoundException extends RuntimeException {
    public SubCategoryNotFoundException(Integer id) {
        super("Could not find 'subCategory' with id = " + id);
    }
}
