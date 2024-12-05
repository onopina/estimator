package com.alinaonopina.estimator.dao;

import com.alinaonopina.estimator.entity.ApartmentCategoryQuantity;
import com.alinaonopina.estimator.entity.Cost;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface StatisticsDao {
    public Map<ApartmentCategoryQuantity, Map<Cost, BigDecimal>> getAllQuantitiesCostsAndTotalCosts();

    public Map<Cost, Map<ApartmentCategoryQuantity, BigDecimal>> getAllCostsQuantitiesAndTotalCosts();

    public List<Cost> getAllCosts();
}
