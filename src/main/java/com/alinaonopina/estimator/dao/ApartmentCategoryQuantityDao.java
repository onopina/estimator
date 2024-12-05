package com.alinaonopina.estimator.dao;

import com.alinaonopina.estimator.entity.Apartment;
import com.alinaonopina.estimator.entity.ApartmentCategoryQuantity;

import java.util.List;

public interface ApartmentCategoryQuantityDao {
    public List<ApartmentCategoryQuantity> getAllApartmentCategoryQuantities();

    public List<ApartmentCategoryQuantity> getCategoriesAndQuantitiesById(int aptId);

    public void saveApartmentCategoryQuantity(ApartmentCategoryQuantity apartmentCategoryQuantity);

    public Apartment getApartmentById(int aptId);

    public ApartmentCategoryQuantity getApartmentCategoryQuantity(int aptId, int categoryId);

    public void deleteApartmentCategoryQuantity(int aptId, int categoryId);
}
