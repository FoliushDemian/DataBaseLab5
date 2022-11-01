package com.example.database5.controller;

import com.example.database5.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
    @ResponseBody
    @ExceptionHandler(CategoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String categoryNotFoundHandler(CategoryNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ShopNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String shopNotFoundHandler(ShopNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(SubCategoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String subCategoryNotFoundHandler(SubCategoryNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String customerNotFoundHandler(CustomerNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ShopAlreadyHasGoodsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String shopHasGoodsHandler(ShopAlreadyHasGoodsException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ShopHasNoGoodsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String shopHasNoGoodsHandler(ShopHasNoGoodsException ex) {
        return ex.getMessage();
    }

}
