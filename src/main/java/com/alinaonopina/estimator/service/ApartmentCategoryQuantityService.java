package com.alinaonopina.estimator.service;

import com.alinaonopina.estimator.entity.Apartment;
import com.alinaonopina.estimator.entity.ApartmentCategoryQuantity;

import java.util.List;

public interface ApartmentCategoryQuantityService {

    public List<ApartmentCategoryQuantity> getCategoriesAndQuantitiesById(int aptId);

    public void saveApartmentCategoryQuantity(ApartmentCategoryQuantity apartmentCategoryQuantity);

    public Apartment getApartmentById(int aptId);

    public ApartmentCategoryQuantity getApartmentCategoryQuantity(int aptId, int categoryId);

    public void deleteApartmentCategoryQuantity(int aptId, int categoryId);
}
