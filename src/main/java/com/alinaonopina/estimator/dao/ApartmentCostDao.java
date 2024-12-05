package com.alinaonopina.estimator.dao;

import com.alinaonopina.estimator.entity.ApartmentCategoryQuantity;
import com.alinaonopina.estimator.entity.Cost;

import java.math.BigDecimal;
import java.util.Map;

public interface ApartmentCostDao {

    public Map<ApartmentCategoryQuantity, Map<Cost, BigDecimal>> getQuantitiesAndCostsById(int aptId);
}

