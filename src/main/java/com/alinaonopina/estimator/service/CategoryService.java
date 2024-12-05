package com.alinaonopina.estimator.service;

import com.alinaonopina.estimator.entity.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> getAllCategories();

    public List<Category> getEmptyCategoriesById(int aptId);

    public Category getCategory(int categoryId);
}
