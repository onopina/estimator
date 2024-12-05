package com.alinaonopina.estimator.service;

import com.alinaonopina.estimator.dao.CostDao;
import com.alinaonopina.estimator.entity.Cost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class CostServiceImpl implements CostService{

    @Autowired
    private CostDao costdao;

    @Override
    @Transactional
    public List<Cost> getAllActualCosts() {
        List<Cost> allActualCosts = costdao.getAllActualCosts();
        Collections.sort(allActualCosts);
        return allActualCosts;
    }

    @Override
    @Transactional
    public void saveCost(Cost cost) {
        //сбрасываем флаг isActive для всех предыдущих значений цены данной работы
        costdao.deactivateCosts(cost);

        //устанавливаем флаг isActive для нового объекта
        cost.setActual(true);

        //сохраняем новый объект
        costdao.saveCost(cost);
    }

    @Override
    @Transactional
    public Cost getCost(int costId) {
        return costdao.getCost(costId);
    }

    @Override
    @Transactional
    public void deactivateCost(int costId) {
        costdao.deactivateCost(costId);
    }
}
