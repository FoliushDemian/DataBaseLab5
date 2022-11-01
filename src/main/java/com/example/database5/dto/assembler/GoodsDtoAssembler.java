
package com.example.database5.dto.assembler;

import com.example.database5.controller.GoodsController;
import com.example.database5.domain.Goods;
import com.example.database5.dto.GoodsDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class GoodsDtoAssembler implements RepresentationModelAssembler<Goods, GoodsDto> {
    @Override
    public GoodsDto toModel(Goods entity) {
        GoodsDto goodsDto = GoodsDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .expirationDate(entity.getExpirationDate())
                .build();
        Link selfLink = linkTo(methodOn(GoodsController.class).getGoods(Math.toIntExact(goodsDto.getId()))).withSelfRel();
        goodsDto.add(selfLink);
        return goodsDto;
    }

    @Override
    public CollectionModel<GoodsDto> toCollectionModel(Iterable<? extends Goods> entities) {
        CollectionModel<GoodsDto> goodsDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(GoodsController.class).getAllGoodss()).withSelfRel();
        goodsDtos.add(selfLink);
        return goodsDtos;
    }

    public CollectionModel<GoodsDto> toCollectionModel(Iterable<? extends Goods> entities, Link link) {
        CollectionModel<GoodsDto> goodsDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        goodsDtos.add(link);
        return goodsDtos;
    }
}
