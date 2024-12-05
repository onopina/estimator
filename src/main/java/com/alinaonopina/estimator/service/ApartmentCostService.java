package com.alinaonopina.estimator.service;

import com.alinaonopina.estimator.entity.ApartmentCategoryQuantity;
import com.alinaonopina.estimator.entity.Cost;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ApartmentCostService {

    public Map<ApartmentCategoryQuantity,Map<Cost, BigDecimal>> getQuantitiesAndCostsById(int aptId);

    public BigDecimal calculateTotalCostByApartmentId(
            Map<ApartmentCategoryQuantity, Map<Cost, BigDecimal>> quantitiesAndCostsById);

    public void refreshCostsByApartmentId(int aptId);

    public List<Cost> getAvailableCostsByApartmentId(int aptId);

    public void saveNewCostsToApartment(List<Integer> newCostsIds, int aptId);

    public void deleteCostFromApartment(int aptId, int costId);
}
