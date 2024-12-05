package com.alinaonopina.estimator.service;

import com.alinaonopina.estimator.dao.ApartmentDao;
import com.alinaonopina.estimator.entity.Apartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ApartmentServiceImpl implements ApartmentService{

    @Autowired
    private ApartmentDao apartmentDao;

    @Override
    @Transactional
    public List<Apartment> getAllApartments() {
        return apartmentDao.getAllApartments();
    }

    @Override
    @Transactional
    public void saveApartment(Apartment apartment) {
        apartmentDao.saveApartment(apartment);
    }

    @Override
    @Transactional
    public Apartment getApartment(int aptId) {
        return apartmentDao.getApartment(aptId);
    }

    @Override
    @Transactional
    public void deleteApartment(int aptId) {
        apartmentDao.deleteApartment(aptId);
    }
}
