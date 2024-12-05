package com.alinaonopina.estimator.dao;

import com.alinaonopina.estimator.entity.Apartment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ApartmentDaoImpl implements ApartmentDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Apartment> getAllApartments() {
        Session session = sessionFactory.getCurrentSession();

        List<Apartment> allApartments = session
                .createQuery("from Apartment", Apartment.class)
                .getResultList();

        return allApartments;
    }

    @Override
    public void saveApartment(Apartment apartment) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(apartment);
    }

    @Override
    public Apartment getApartment(int aptId) {
        Session session = sessionFactory.getCurrentSession();

        Apartment apartment = session.get(Apartment.class, aptId);

        if (apartment == null) {
            throw new IllegalArgumentException("Apartment с таким id не найден: " + aptId);
        }

        return apartment;
    }

    @Override
    public void deleteApartment(int aptId) {
        Session session = sessionFactory.getCurrentSession();

        Query<Apartment> query = session.createQuery("DELETE FROM Apartment " +
                "WHERE id =:aptId");

        query.setParameter("aptId", aptId);

        query.executeUpdate();
    }
}
