package com.example.database5.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "customer", collectionRelation = "customers")
public class CustomerDto extends RepresentationModel<CustomerDto> {
    private Integer id;
    private String name;
    private String email;
    private String phoneNumber;
}
