package com.alinaonopina.estimator.dao;

import com.alinaonopina.estimator.entity.ApartmentCategoryQuantity;
import com.alinaonopina.estimator.entity.Cost;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Repository
public class StatisticsDaoImpl implements StatisticsDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Map<ApartmentCategoryQuantity, Map<Cost, BigDecimal>> getAllQuantitiesCostsAndTotalCosts() {
        Session session = sessionFactory.getCurrentSession();

        String hqlRequest = "SELECT acq, c, c.costValue*acq.quantity FROM ApartmentCategoryQuantity AS acq " +
                "JOIN Apartment AS a ON acq.apartment.id=a.id " +
                "JOIN a.costs AS c " +
                "WHERE acq.category=c.category";

        List<Object[]> results = session.createQuery(hqlRequest).getResultList();

        Map<ApartmentCategoryQuantity, Map<Cost, BigDecimal>> allQuantitesCostsAndTotalCosts = new TreeMap<>();

        for (Object[] result : results) {
            ApartmentCategoryQuantity acq = (ApartmentCategoryQuantity) result[0];
            Cost cost = (Cost) result[1];
            BigDecimal totalCost = (BigDecimal) result[2];

            allQuantitesCostsAndTotalCosts.computeIfAbsent(acq, k -> new HashMap<>())
                    .put(cost, totalCost);
        }

        return allQuantitesCostsAndTotalCosts;
    }

    @Override
    public Map<Cost, Map<ApartmentCategoryQuantity, BigDecimal>> getAllCostsQuantitiesAndTotalCosts() {
        Session session = sessionFactory.getCurrentSession();

        String hqlRequest = "SELECT c, acq, c.costValue * acq.quantity FROM Cost AS c " +
                "JOIN c.apartments AS a " +
                "JOIN a.apartmentCategoryQuantities AS acq " +
                "WHERE acq.category = c.category";

        List<Object[]> results = session.createQuery(hqlRequest).getResultList();

        Map<Cost, Map<ApartmentCategoryQuantity, BigDecimal>> allCostsQuantitiesAndTotalCosts =
                new TreeMap<>();

        for (Object[] result : results) {
            Cost cost = (Cost) result[0];
            ApartmentCategoryQuantity acq = (ApartmentCategoryQuantity) result[1];
            BigDecimal totalCost = (BigDecimal) result[2];
            allCostsQuantitiesAndTotalCosts.computeIfAbsent(cost, k -> new HashMap<>())
                    .put(acq, totalCost);
        }

        return allCostsQuantitiesAndTotalCosts;
    }

    @Override
    public List<Cost> getAllCosts() {
        Session session = sessionFactory.getCurrentSession();

        List<Cost> allCosts = session
                .createQuery("FROM Cost", Cost.class)
                .getResultList();

        return allCosts;
    }
}
