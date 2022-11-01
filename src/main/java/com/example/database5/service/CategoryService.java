
package com.example.database5.service;

import com.example.database5.domain.Category;

import java.util.List;

public interface CategoryService extends GeneralService<Category, Integer> {
    List<Category> findCategoryByName(String name);
}
