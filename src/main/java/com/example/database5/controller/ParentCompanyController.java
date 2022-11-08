package com.example.database5.controller;

import com.example.database5.domain.ParentCompany;
import com.example.database5.dto.ParentCompanyDto;
import com.example.database5.dto.assembler.ParentCompanyDtoAssembler;
import com.example.database5.service.ParentCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/parentCompanies")
public class ParentCompanyController {

    @Autowired
    private ParentCompanyService parentCompanyService;
    @Autowired
    private ParentCompanyDtoAssembler parentCompanyDtoAssembler;

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<ParentCompanyDto>> getAllParentCompanies() {
        List<ParentCompany> parentCompanies = parentCompanyService.findAll();
        CollectionModel<ParentCompanyDto> parentCompanyDtos = parentCompanyDtoAssembler.toCollectionModel(parentCompanies);
        return new ResponseEntity<>(parentCompanyDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ParentCompanyDto> getParentCompany(@PathVariable Integer id) {
        ParentCompany parentCompany = parentCompanyService.findById(id);
        ParentCompanyDto parentCompanyDto = parentCompanyDtoAssembler.toModel(parentCompany);
        return new ResponseEntity<>(parentCompanyDto, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<ParentCompanyDto> addParentCompany(@RequestBody ParentCompany parentCompany) {
        ParentCompany newParentCompany = parentCompanyService.create(parentCompany);
        ParentCompanyDto parentCompanyDto = parentCompanyDtoAssembler.toModel(newParentCompany);
        return new ResponseEntity<>(parentCompanyDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateParentCompany(@RequestBody ParentCompany uParentCompany, @PathVariable Integer id) {
        parentCompanyService.update(id, uParentCompany);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteParentCompany(@PathVariable Integer id) {
        parentCompanyService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
