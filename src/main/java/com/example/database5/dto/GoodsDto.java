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
@Relation(itemRelation = "goods", collectionRelation = "goodss")
public class GoodsDto extends RepresentationModel<GoodsDto>{
    private Integer id;
    private String name;
    private Integer price;
    private String expirationDate;
    private String customer;
}
