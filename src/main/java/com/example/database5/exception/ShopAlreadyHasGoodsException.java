
package com.example.database5.exception;

public class ShopAlreadyHasGoodsException extends RuntimeException {
    public ShopAlreadyHasGoodsException(Integer goodsId, Integer shopId){
        super("'shop' with id=" + shopId +  " already have 'goods' with id=" + goodsId);
    }
}
