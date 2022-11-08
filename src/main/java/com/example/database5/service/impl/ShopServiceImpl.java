package com.example.database5.service.impl;

import com.example.database5.domain.Goods;
import com.example.database5.domain.Shop;
import com.example.database5.exception.ShopNotFoundException;
import com.example.database5.repository.ShopRepository;
import com.example.database5.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    ShopRepository shopRepository;

    @Override
    public Integer getAverageMinOrderAmount() {
        return shopRepository.getAverageMinOrderAmount();
    }

    @Override
    public void addShopGoodsRelationship(String shopName, String goodsName) {
        shopRepository.addShopGoodsRelationship(shopName, goodsName);
    }

    @Override
    public Shop addShopWithProcedure(String name, Integer minOrderAmount, Integer parentCompanyId) {
        return shopRepository.addShopWithProcedure(name, minOrderAmount, parentCompanyId);
    }


    @Override
    public List<Goods> findAllGoodssByShopId(Integer shopId) {
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new ShopNotFoundException(shopId));
        return shop.getGoodss().stream().toList();
    }

    @Override
    public List<Shop> findAll() {
        return shopRepository.findAll();
    }

    @Override
    public Shop findById(Integer id) {
        return shopRepository.findById(id)
                .orElseThrow(() -> new ShopNotFoundException(id));
    }

    @Override
    public Shop create(Shop shop) {
        shopRepository.save(shop);
        return shop;
    }

    @Override
    public void update(Integer id, Shop uShop) {
        Shop shop = shopRepository.findById(id)
                .orElseThrow(() -> new ShopNotFoundException(id));
        shop.setName(uShop.getName());
        shop.setMinOrderAmount(uShop.getMinOrderAmount());
        shopRepository.save(shop);
    }

    @Override
    public void delete(Integer id) {
        Shop shop = shopRepository.findById(id)
                .orElseThrow(() -> new ShopNotFoundException(id));
        shopRepository.delete(shop);
    }
}
