package com.alinaonopina.estimator.dao;

import com.alinaonopina.estimator.entity.Cost;

import java.util.List;

public interface CostDao {
    public List<Cost> getAllActualCosts();

    public void saveCost(Cost cost);

    public void deactivateCosts(Cost cost);

    public Cost getCost(int costId);

    public void deactivateCost(int costId);

    public List<Cost> getCostsByCostsIds(List<Integer> costsIds);
}
