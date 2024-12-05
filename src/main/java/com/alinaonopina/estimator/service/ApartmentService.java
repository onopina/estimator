package com.alinaonopina.estimator.service;

import com.alinaonopina.estimator.entity.Apartment;

import java.util.List;

public interface ApartmentService {
    public List<Apartment> getAllApartments();

    public void saveApartment(Apartment apartment);

    public Apartment getApartment(int aptId);

    public void deleteApartment(int aptId);
}
