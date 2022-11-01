/**
 * @author RoSteik (Telegram: @RoSteik)
 * Project: rostyk
 * Package: iot.lviv.ua.rostyk.exception
 * Class: DriverHasNoCarException
 */

package com.example.database5.exception;

public class ShopHasNoGoodsException extends RuntimeException {
    public ShopHasNoGoodsException(Integer shopId, Integer goodsId){
        super("'shop' with id=" + shopId +  " doesn't have 'goods' with id=" + goodsId);
    }
}
