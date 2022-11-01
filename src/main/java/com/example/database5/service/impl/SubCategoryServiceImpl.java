package com.example.database5.service.impl;

import com.example.database5.domain.SubCategory;
import com.example.database5.exception.SubCategoryNotFoundException;
import com.example.database5.repository.SubCategoryRepository;
import com.example.database5.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    @Autowired
    SubCategoryRepository subCategoryRepository;

    @Override
    public List<SubCategory> findAll() {
        return subCategoryRepository.findAll();
    }

    @Override
    public SubCategory findById(Integer id) {
        return subCategoryRepository.findById(id)
                .orElseThrow(() -> new SubCategoryNotFoundException(id));
    }

    @Override
    public SubCategory create(SubCategory subCategory) {
        subCategoryRepository.save(subCategory);
        return subCategory;
    }

    @Override
    public void update(Integer id, SubCategory uSubCategory) {
        SubCategory subCategory = subCategoryRepository.findById(id)
                .orElseThrow(() -> new SubCategoryNotFoundException(id));
        //update
        subCategory.setName(uSubCategory.getName());
        subCategoryRepository.save(subCategory);
    }

    @Override
    public void delete(Integer id) {
        SubCategory subCategory = subCategoryRepository.findById(id)
                .orElseThrow(() -> new SubCategoryNotFoundException(id));
        subCategoryRepository.delete(subCategory);
    }
}
