package com.example.database5.service.impl;

import com.example.database5.domain.Customer;
import com.example.database5.domain.Goods;
import com.example.database5.exception.CustomerHasNoGoodsException;
import com.example.database5.exception.GoodsNotFoundException;
import com.example.database5.repository.CustomerRepository;
import com.example.database5.repository.GoodsRepository;
import com.example.database5.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsRepository goodsRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public void createTablesWithCursor() {
        goodsRepository.createTablesWithCursor();
    }

    @Override
    public List<Goods> findAll() {
        return goodsRepository.findAll();
    }

    @Override
    public Goods findById(Integer id) {
        return goodsRepository.findById(id)
                .orElseThrow(() -> new GoodsNotFoundException(id));
    }

    @Override
    public Goods create(Goods goods) {
        goodsRepository.save(goods);
        return goods;
    }

    @Override
    public void update(Integer id, Goods uGoods) {
        Goods goods = goodsRepository.findById(id)
                .orElseThrow(() -> new GoodsNotFoundException(id));
        //update
        goods.setName(uGoods.getName());
        goods.setPrice(uGoods.getPrice());
        goods.setExpirationDate(uGoods.getExpirationDate());
        goodsRepository.save(goods);
    }

    @Override
    public void delete(Integer id) {
        Goods goods = goodsRepository.findById(id)
                .orElseThrow(() -> new GoodsNotFoundException(id));
        goodsRepository.delete(goods);
    }
    @Override
    public List<Goods> findGoodssByCustomerId(Integer customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerHasNoGoodsException(customerId));
        return customer.getGoodss();
    }
}
