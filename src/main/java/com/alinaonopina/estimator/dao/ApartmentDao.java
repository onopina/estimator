package com.alinaonopina.estimator.dao;

import com.alinaonopina.estimator.entity.Apartment;

import java.util.List;

public interface ApartmentDao {

    public List<Apartment> getAllApartments();

    public void saveApartment(Apartment apartment);

    public Apartment getApartment(int aptId);

    public void deleteApartment(int aptId);
}
