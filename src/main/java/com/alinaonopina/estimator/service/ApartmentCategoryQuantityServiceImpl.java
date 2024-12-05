package com.alinaonopina.estimator.service;

import com.alinaonopina.estimator.dao.ApartmentCategoryQuantityDao;
import com.alinaonopina.estimator.entity.Apartment;
import com.alinaonopina.estimator.entity.ApartmentCategoryQuantity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ApartmentCategoryQuantityServiceImpl implements ApartmentCategoryQuantityService{

    @Autowired
    private ApartmentCategoryQuantityDao apartmentCategoryQuantityDao;

    @Override
    @Transactional
    public List<ApartmentCategoryQuantity> getCategoriesAndQuantitiesById(int aptId) {
        return apartmentCategoryQuantityDao.getCategoriesAndQuantitiesById(aptId);
    }

    @Override
    @Transactional
    public void saveApartmentCategoryQuantity(ApartmentCategoryQuantity apartmentCategoryQuantity) {
        apartmentCategoryQuantityDao.saveApartmentCategoryQuantity(apartmentCategoryQuantity);
    }

    @Override
    @Transactional
    public Apartment getApartmentById(int aptId) {
        return apartmentCategoryQuantityDao.getApartmentById(aptId);
    }

    @Override
    @Transactional
    public ApartmentCategoryQuantity getApartmentCategoryQuantity(int aptId, int categoryId) {
        return apartmentCategoryQuantityDao.getApartmentCategoryQuantity(aptId, categoryId);
    }

    @Override
    @Transactional
    public void deleteApartmentCategoryQuantity(int aptId, int categoryId) {
        apartmentCategoryQuantityDao.deleteApartmentCategoryQuantity(aptId, categoryId);
    }
}
