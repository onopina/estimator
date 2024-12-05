package com.alinaonopina.estimator.service;

import com.alinaonopina.estimator.entity.Cost;

import java.util.List;

public interface CostService {
    public List<Cost> getAllActualCosts();

    public void saveCost(Cost cost);

    public Cost getCost(int costId);

    public void deactivateCost(int costId);
}
