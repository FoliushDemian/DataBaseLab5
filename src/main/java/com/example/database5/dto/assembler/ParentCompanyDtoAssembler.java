package com.example.database5.dto.assembler;

import com.example.database5.controller.ParentCompanyController;
import com.example.database5.domain.ParentCompany;
import com.example.database5.dto.ParentCompanyDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class ParentCompanyDtoAssembler implements RepresentationModelAssembler<ParentCompany, ParentCompanyDto> {

    @Override
    public ParentCompanyDto toModel(ParentCompany entity) {
        ParentCompanyDto parentCompanyDto = ParentCompanyDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
        Link selfLink = linkTo(methodOn(ParentCompanyController.class).getParentCompany(parentCompanyDto.getId())).withSelfRel();
        parentCompanyDto.add(selfLink);
        return parentCompanyDto;
    }

    @Override
    public CollectionModel<ParentCompanyDto> toCollectionModel(Iterable<? extends ParentCompany> entities) {
        CollectionModel<ParentCompanyDto> parentCompanyDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(ParentCompanyController.class).getAllParentCompanies()).withSelfRel();
        parentCompanyDtos.add(selfLink);
        return parentCompanyDtos;
    }
}
