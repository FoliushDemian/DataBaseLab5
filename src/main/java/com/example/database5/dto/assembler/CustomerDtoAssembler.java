
package com.example.database5.dto.assembler;

import com.example.database5.controller.CustomerController;
import com.example.database5.domain.Customer;
import com.example.database5.dto.CustomerDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CustomerDtoAssembler implements RepresentationModelAssembler<Customer, CustomerDto> {
    @Override
    public CustomerDto toModel(Customer entity) {
        CustomerDto customerDto = CustomerDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .phoneNumber(entity.getPhoneNumber())
                .build();
        Link selfLink = linkTo(methodOn(CustomerController.class).getCustomer(Math.toIntExact(customerDto.getId()))).withSelfRel();
        customerDto.add(selfLink);
        return customerDto;
    }

    @Override
    public CollectionModel<CustomerDto> toCollectionModel(Iterable<? extends Customer> entities) {
        CollectionModel<CustomerDto> customerDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(CustomerController.class).getAllCustomers()).withSelfRel();
        customerDtos.add(selfLink);
        return customerDtos;
    }

    public CollectionModel<CustomerDto> toCollectionModel(Iterable<? extends Customer> entities, Link link) {
        CollectionModel<CustomerDto> customerDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        customerDtos.add(link);
        return customerDtos;
    }
}