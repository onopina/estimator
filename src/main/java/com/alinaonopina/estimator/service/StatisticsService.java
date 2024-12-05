package com.alinaonopina.estimator.service;

import com.alinaonopina.estimator.entity.Apartment;
import com.alinaonopina.estimator.entity.ApartmentCategoryQuantity;
import com.alinaonopina.estimator.entity.Category;
import com.alinaonopina.estimator.entity.Cost;
import javafx.util.Pair;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface StatisticsService {
    public Map<ApartmentCategoryQuantity, Map<Cost, BigDecimal>> getAllQuantitiesCostsAndTotalCosts();

    public Map<Apartment, BigDecimal> calculateTotalCostsPerApartments();

    public BigDecimal calculateOverallTotalCostPerPerApartments(Map<Apartment, BigDecimal> totalCostsPerApartments);

    public Map<Cost, Map<ApartmentCategoryQuantity, BigDecimal>> getAllCostsQuantitiesAndTotalCosts();

    public Map<Pair<String, Category>, Pair<Integer, BigDecimal>> calculateTotalCostsPerWorkTitles();

    public BigDecimal calculateOverallTotalCostPerWorkTitles(Map<Pair<String, Category>, Pair<Integer, BigDecimal>>
                                                                     totalCostsPerWorkTitles);
    public List<Cost> getAllLatestCosts();

    public Map<String, Map<Date, BigDecimal>> getAllWorkTitlesDatesAndCostValues();
}
