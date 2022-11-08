package com.example.database5.dto;

import com.example.database5.domain.ParentCompany;
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
@Relation(itemRelation = "parentCompany", collectionRelation = "parentCompanies")
public class ParentCompanyDto extends RepresentationModel<ParentCompanyDto> {
    private final Integer id;
    private final String name;

}
