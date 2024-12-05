package com.alinaonopina.estimator.service;

import com.alinaonopina.estimator.dao.CategoryDao;
import com.alinaonopina.estimator.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryDao categoryDao;

    @Override
    @Transactional
    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    }

    @Override
    @Transactional
    public List<Category> getEmptyCategoriesById(int aptId) {
        return categoryDao.getEmptyCategoriesById(aptId);
    }

    @Override
    @Transactional
    public Category getCategory(int categoryId) {
        return categoryDao.getCategory(categoryId);
    }
}
