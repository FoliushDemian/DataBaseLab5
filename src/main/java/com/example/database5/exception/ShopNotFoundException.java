
package com.example.database5.exception;

public class ShopNotFoundException extends RuntimeException {
    public ShopNotFoundException(Integer id) {
        super("Could not find 'shop' with id = " + id);
    }
}
