package com.example.database5.controller;


import com.example.database5.domain.Goods;
import com.example.database5.domain.Shop;
import com.example.database5.dto.GoodsDto;
import com.example.database5.dto.ShopDto;
import com.example.database5.dto.assembler.GoodsDtoAssembler;
import com.example.database5.dto.assembler.ShopDtoAssembler;
import com.example.database5.service.ShopService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.transaction.Transactional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

@RestController
@RequestMapping(value = "/api/shops")
public class ShopController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopDtoAssembler shopDtoAssembler;

    @Autowired
    private GoodsDtoAssembler goodsDtoAssembler;

    @GetMapping(value = "/average_min_order_amount")
    public ResponseEntity<Integer> getAverageMinOrderAmount() {
        Integer avgMinOrderAmount = shopService.getAverageMinOrderAmount();
        return new ResponseEntity<>(avgMinOrderAmount, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<ShopDto>> getAllShops() {
        List<Shop> shops = shopService.findAll();
        CollectionModel<ShopDto> shopDtos = shopDtoAssembler.toCollectionModel(shops);
        return new ResponseEntity<>(shopDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{shopId}")
    public ResponseEntity<ShopDto> getShop(@PathVariable Integer shopId) {
        Shop shop = shopService.findById(shopId);
        ShopDto shopDto = shopDtoAssembler.toModel(shop);
        return new ResponseEntity<>(shopDto, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<ShopDto> addShop(@RequestBody Shop shop) {
        Shop newShop = shopService.create(shop);
        ShopDto shopDto = shopDtoAssembler.toModel(newShop);
        return new ResponseEntity<>(shopDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{shopId}")
    public ResponseEntity<?> updateShop(@RequestBody Shop uShop, @PathVariable Integer shopId) {
        shopService.update(shopId, uShop);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{shopId}")
    public ResponseEntity<?> deleteShop(@PathVariable Integer shopId) {
        shopService.delete(shopId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{shopId}/goodss")
    public ResponseEntity<CollectionModel<GoodsDto>> getAllGoodssForShop(@PathVariable Integer shopId) {
        List<Goods> goodss = shopService.findAllGoodssByShopId(shopId);
        Link selfLink = linkTo(methodOn(ShopController.class).getAllGoodssForShop(shopId)).withSelfRel();
        CollectionModel<GoodsDto> goodsDtos = goodsDtoAssembler.toCollectionModel(goodss, selfLink);
        return new ResponseEntity<>(goodsDtos, HttpStatus.OK);
    }

    @Transactional
    @PostMapping(value = "/procedure_insert")
    public ResponseEntity<ShopDto> addShopWithProcedure(@RequestBody Shop shop) {
        Shop newShop = shopService.addShopWithProcedure(shop.getName(), shop.getMinOrderAmount(), shop.getParentCompanyId());
        ShopDto shopDto = shopDtoAssembler.toModel(newShop);
        return new ResponseEntity<>(shopDto, HttpStatus.CREATED);
    }

    @Transactional
    @PostMapping(value = "/relationship")
    public ResponseEntity<?> addShopGoodsRelationship(@RequestBody JSONObject jsonObject) {
        shopService.addShopGoodsRelationship(jsonObject.getAsString("shop_name"), jsonObject.getAsString("goods_name"));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
