package com.example.database5.exception;

public class CustomerHasNoGoodsException extends RuntimeException{
    public CustomerHasNoGoodsException(Integer goodsId){
        super("'customer'" +  " doesn't have 'goods' with id=" + goodsId);
    }
}
