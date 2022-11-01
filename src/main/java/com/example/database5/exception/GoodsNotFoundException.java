package com.example.database5.exception;

public class GoodsNotFoundException extends RuntimeException {
    public GoodsNotFoundException(Integer goodsId){
        super("'goods' with id=" + goodsId + "not found");
    }
}
