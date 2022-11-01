package com.example.database5.controller;

import com.example.database5.domain.SubCategory;
import com.example.database5.dto.SubCategoryDto;
import com.example.database5.dto.assembler.SubCategoryDtoAssembler;
import com.example.database5.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/subCategorys")
public class SubCategoryController {
    @Autowired
    private SubCategoryService subCategoryService;
    @Autowired
    private SubCategoryDtoAssembler subCategoryDtoAssembler;

    @GetMapping(value = "/{subCategoryId}")
    public ResponseEntity<SubCategoryDto> getSubCategory(@PathVariable Integer subCategoryId) {
        SubCategory subCategory = subCategoryService.findById(subCategoryId);
        SubCategoryDto subCategoryDto = subCategoryDtoAssembler.toModel(subCategory);
        return new ResponseEntity<>(subCategoryDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<SubCategoryDto>> getAllSubCategorys() {
        List<SubCategory> subCategorys = subCategoryService.findAll();
        CollectionModel<SubCategoryDto> subCategoryDtos = subCategoryDtoAssembler.toCollectionModel(subCategorys);
        return new ResponseEntity<>(subCategoryDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<SubCategoryDto> addSubCategory(@RequestBody SubCategory subCategory) {
        SubCategory newSubCategory = subCategoryService.create(subCategory);
        SubCategoryDto subCategoryDto = subCategoryDtoAssembler.toModel(newSubCategory);
        return new ResponseEntity<>(subCategoryDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{subCategoryId}")
    public ResponseEntity<?> updateSubCategory(@RequestBody SubCategory uSubCategory, @PathVariable Integer subCategoryId) {
        subCategoryService.update(subCategoryId, uSubCategory);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{subCategoryId}")
    public ResponseEntity<?> deleteSubCategory(@PathVariable Integer subCategoryId) {
        subCategoryService.delete(subCategoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
