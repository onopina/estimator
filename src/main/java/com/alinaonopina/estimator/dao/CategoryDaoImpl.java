package com.alinaonopina.estimator.dao;

import com.alinaonopina.estimator.entity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Category> getAllCategories() {
        Session session = sessionFactory.getCurrentSession();
        List<Category> allCategories = session
                .createQuery("FROM Category", Category.class)
                .getResultList();

        return allCategories;
    }

    @Override
    public List<Category> getEmptyCategoriesById(int aptId) {  //возвращает пустые категории у объекта
        Session session = sessionFactory.getCurrentSession();

        String hqlRequest = "SELECT c " +
                "FROM Category AS c " +
                "LEFT JOIN ApartmentCategoryQuantity AS acq " +
                "ON c.id = acq.category.id AND acq.apartment.id = :aptId " +
                "WHERE acq.category.id IS NULL";

        List<Category> emptyCategoriesById = session
                .createQuery(hqlRequest, Category.class)
                .setParameter("aptId", aptId)
                .getResultList();

        return emptyCategoriesById;
    }

    @Override
    public Category getCategory(int categoryId) {
        Session session = sessionFactory.getCurrentSession();

        Category category = session.get(Category.class, categoryId);

        if (category == null) {
            throw new IllegalArgumentException("Категория с таким id не найдена: " + categoryId);
        }

        return category;
    }
}
