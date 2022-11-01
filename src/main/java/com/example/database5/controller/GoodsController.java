package com.example.database5.controller;

import com.example.database5.domain.Goods;
import com.example.database5.dto.GoodsDto;
import com.example.database5.dto.assembler.GoodsDtoAssembler;
import com.example.database5.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.util.List;


@RestController
@RequestMapping(value = "/api/goodss")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsDtoAssembler goodsDtoAssembler;

//    @GetMapping(value = "/customers/{customerId}")
//    public ResponseEntity<CollectionModel<GoodsDto>> getAllGoodsForCustomer(@PathVariable Integer customerId) {
//        List<Goods> goodss = goodsService.findGoodsByCustomerId(customerId);
//        Link selfLink = linkTo(methodOn(GoodsController.class).getAllGoodsForCustomer(customerId)).withSelfRel();
//        CollectionModel<GoodsDto> goodsDtos = goodsDtoAssembler.toCollectionModel(goodss, selfLink);
//        return new ResponseEntity<>(goodsDtos, HttpStatus.OK);
//    }


    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<GoodsDto>> getAllGoodss() {
        List<Goods> goodss = goodsService.findAll();
        CollectionModel<GoodsDto> goodsDtos = goodsDtoAssembler.toCollectionModel(goodss);
        return new ResponseEntity<>(goodsDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{goodsId}")
    public ResponseEntity<GoodsDto> getGoods(@PathVariable Integer goodsId) {
        Goods goods = goodsService.findById(goodsId);
        GoodsDto goodsDto = goodsDtoAssembler.toModel(goods);
        return new ResponseEntity<>(goodsDto, HttpStatus.OK);
    }

    @GetMapping(value = "customers/{customerId}")
    public ResponseEntity<CollectionModel<GoodsDto>> getGoodssByCustomerId(
            @PathVariable Integer customerId) {
        List<Goods> goodss = goodsService.findGoodssByCustomerId(customerId);
        Link selfLink = linkTo(methodOn(GoodsController.class).getGoodssByCustomerId(customerId))
                .withSelfRel();
        CollectionModel<GoodsDto> goodsDtos = goodsDtoAssembler.
                toCollectionModel(goodss, selfLink);
        return new ResponseEntity<>(goodsDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<GoodsDto> addGoods(@RequestBody Goods goods) {
        Goods newGoods = goodsService.create(goods);
        GoodsDto goodsDto = goodsDtoAssembler.toModel(newGoods);
        return new ResponseEntity<>(goodsDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{goodsId}")
    public ResponseEntity<?> updateGoods(@RequestBody Goods uGoods, @PathVariable Integer goodsId) {
        goodsService.update(goodsId, uGoods);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{goodsId}")
    public ResponseEntity<?> deleteGoods(@PathVariable Integer goodsId) {
        goodsService.delete(goodsId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
