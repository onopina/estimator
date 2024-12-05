package com.alinaonopina.estimator.dao;

import com.alinaonopina.estimator.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ApartmentCategoryQuantityDaoImpl implements ApartmentCategoryQuantityDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<ApartmentCategoryQuantity> getAllApartmentCategoryQuantities() {
        Session session = sessionFactory.getCurrentSession();
        List<ApartmentCategoryQuantity> allApartmentCategoryQuantities = session
                .createQuery("FROM ApartmentCategoryQuantity", ApartmentCategoryQuantity.class)
                .getResultList();

        return allApartmentCategoryQuantities;
    }

    @Override
    public List<ApartmentCategoryQuantity> getCategoriesAndQuantitiesById(int aptId) {
        Session session = sessionFactory.getCurrentSession();

        String hqlRequest = "SELECT acq FROM ApartmentCategoryQuantity AS acq " +
                "WHERE acq.id.apartmentId = :aptId";

        List<ApartmentCategoryQuantity> categoriesAndQuantitiesById = session
                .createQuery(hqlRequest, ApartmentCategoryQuantity.class)
                .setParameter("aptId", aptId)
                .getResultList();

        return categoriesAndQuantitiesById;
    }

    @Override
    public void saveApartmentCategoryQuantity(ApartmentCategoryQuantity apartmentCategoryQuantity) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(apartmentCategoryQuantity);
    }

    @Override
    public Apartment getApartmentById(int aptId) {
        Session session = sessionFactory.getCurrentSession();

        Apartment apartment = session.get(Apartment.class, aptId);

        if (apartment == null) {
            throw new IllegalArgumentException("Apartment с таким id не найден: " + aptId);
        }

        return apartment;
    }

    @Override
    public ApartmentCategoryQuantity getApartmentCategoryQuantity(int aptId, int categoryId) {
        Session session = sessionFactory.getCurrentSession();

        String hqlRequest = "SELECT acq FROM ApartmentCategoryQuantity AS acq " +
                "WHERE (acq.id.apartmentId = :aptId AND acq.id.categoryId = :categoryId)";

        ApartmentCategoryQuantity apartmentCategoryQuantity = session
                .createQuery(hqlRequest, ApartmentCategoryQuantity.class)
                .setParameter("aptId", aptId)
                .setParameter("categoryId", categoryId)
                .getSingleResult();

        return apartmentCategoryQuantity;
    }

    @Override
    public void deleteApartmentCategoryQuantity(int aptId, int categoryId) {
        Session session = sessionFactory.getCurrentSession();

        Query<Cost> query = session.createQuery("DELETE FROM ApartmentCategoryQuantity AS acq " +
                "WHERE (acq.apartment.id = :aptId AND acq.category.id = :categoryId)");

        query.setParameter("aptId", aptId);
        query.setParameter("categoryId", categoryId);

        query.executeUpdate();
    }
}
