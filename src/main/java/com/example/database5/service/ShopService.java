package com.example.database5.service;

import com.example.database5.domain.Goods;
import com.example.database5.domain.Shop;

import java.util.List;

public interface ShopService extends GeneralService<Shop, Integer> {

    List<Goods> findAllGoodssByShopId(Integer shopId);

    Integer getAverageMinOrderAmount();
    void addShopGoodsRelationship(String shopName, String goodsName);

    Shop addShopWithProcedure(String name, Integer minOrderAmount, Integer parentCompanyId);
}
