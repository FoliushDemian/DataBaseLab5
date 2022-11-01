package com.example.database5.dto.assembler;

import com.example.database5.controller.SubCategoryController;
import com.example.database5.domain.SubCategory;
import com.example.database5.dto.SubCategoryDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SubCategoryDtoAssembler implements RepresentationModelAssembler<SubCategory, SubCategoryDto> {
    @Override
    public SubCategoryDto toModel(SubCategory entity) {
        SubCategoryDto subCategoryDto = SubCategoryDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
        Link selfLink = linkTo(methodOn(SubCategoryController.class).getSubCategory(Math.toIntExact(subCategoryDto.getId()))).withSelfRel();
        subCategoryDto.add(selfLink);
        return subCategoryDto;
    }

    @Override
    public CollectionModel<SubCategoryDto> toCollectionModel(Iterable<? extends SubCategory> entities) {
        CollectionModel<SubCategoryDto> subCategoryDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(SubCategoryController.class).getAllSubCategorys()).withSelfRel();
        subCategoryDtos.add(selfLink);
        return subCategoryDtos;
    }

    public CollectionModel<SubCategoryDto> toCollectionModel(Iterable<? extends SubCategory> entities, Link link) {
        CollectionModel<SubCategoryDto> subCategoryDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        subCategoryDtos.add(link);
        return subCategoryDtos;
    }
}
