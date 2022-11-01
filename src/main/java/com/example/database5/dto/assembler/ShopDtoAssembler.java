
package com.example.database5.dto.assembler;

import com.example.database5.controller.ShopController;
import com.example.database5.domain.Shop;
import com.example.database5.dto.ShopDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ShopDtoAssembler implements RepresentationModelAssembler<Shop, ShopDto> {

    @Override
    public ShopDto toModel(Shop entity) {
        ShopDto shopDto = ShopDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .minOrderAmount(entity.getMinOrderAmount())
                .build();
        Link selfLink = linkTo(methodOn(ShopController.class).getShop(Math.toIntExact(shopDto.getId()))).withSelfRel();
        shopDto.add(selfLink);
        Link goodsLink = linkTo(methodOn(ShopController.class).getAllGoodssForShop(Math.toIntExact(entity.getId()))).withRel("goodss");
        shopDto.add(goodsLink);
        return shopDto;
    }

    @Override
    public CollectionModel<ShopDto> toCollectionModel(Iterable<? extends Shop> entities) {
        CollectionModel<ShopDto> shopDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(ShopController.class).getAllShops()).withSelfRel();
        shopDtos.add(selfLink);
        return shopDtos;
    }

    public CollectionModel<ShopDto> toCollectionModel(Iterable<? extends Shop> entities, Link link) {
        CollectionModel<ShopDto> shopDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        shopDtos.add(link);
        return shopDtos;
    }
}
