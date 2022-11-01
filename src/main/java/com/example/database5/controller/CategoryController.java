package com.example.database5.controller;



import com.example.database5.domain.Category;
import com.example.database5.dto.CategoryDto;
import com.example.database5.dto.assembler.CategoryDtoAssembler;

import com.example.database5.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryDtoAssembler categoryDtoAssembler;

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<CategoryDto>> getAllCategories() {
        List<Category> categorys = categoryService.findAll();
        CollectionModel<CategoryDto> categoryDtos = categoryDtoAssembler.toCollectionModel(categorys);
        return new ResponseEntity<>(categoryDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId) {
        Category category = categoryService.findById(categoryId);
        CategoryDto categoryDto = categoryDtoAssembler.toModel(category);
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<CollectionModel<CategoryDto>> getCategoryByBrand(@PathVariable String name) {
        List<Category> categories = categoryService.findCategoryByName(name);
        CollectionModel<CategoryDto> categoryDtos = categoryDtoAssembler.toCollectionModel(categories);
        return new ResponseEntity<>(categoryDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody Category category) {
        Category newCategory = categoryService.create(category);
        CategoryDto categoryDto = categoryDtoAssembler.toModel(newCategory);
        return new ResponseEntity<>(categoryDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{categoryId}")
    public ResponseEntity<?> updateCategory(@RequestBody Category uCategory, @PathVariable Integer categoryId) {
        categoryService.update(categoryId, uCategory);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer categoryId) {
        categoryService.delete(categoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
