package com.alinaonopina.estimator.dao;

import com.alinaonopina.estimator.entity.Category;

import java.util.List;

public interface CategoryDao {
    public List<Category> getAllCategories();

    public List<Category> getEmptyCategoriesById(int aptId);

    public Category getCategory(int categoryId);
}
