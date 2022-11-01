package com.example.database5.service;

import com.example.database5.domain.Goods;

import java.util.List;

public interface GoodsService extends GeneralService<Goods, Integer> {
    List<Goods> findGoodssByCustomerId(Integer customerId);
}
