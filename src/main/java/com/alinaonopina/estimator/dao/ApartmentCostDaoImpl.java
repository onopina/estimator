package com.alinaonopina.estimator.dao;

import com.alinaonopina.estimator.entity.ApartmentCategoryQuantity;
import com.alinaonopina.estimator.entity.Cost;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;

@Repository
public class ApartmentCostDaoImpl implements ApartmentCostDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Map<ApartmentCategoryQuantity, Map<Cost, BigDecimal>> getQuantitiesAndCostsById(int aptId) {
        Session session = sessionFactory.getCurrentSession();

        String hqlReguest = "SELECT acq, c, c.costValue*acq.quantity FROM ApartmentCategoryQuantity AS acq " +
                "JOIN Apartment AS a ON acq.apartment.id=a.id " +
                "JOIN a.costs AS c " +
                "WHERE acq.category=c.category AND a.id = :aptId";

        List<Object[]> results = session
                .createQuery(hqlReguest)
                .setParameter("aptId", aptId)
                .getResultList();

        Map<ApartmentCategoryQuantity, Map<Cost, BigDecimal>> quantitesAndCostsById = new TreeMap<>();

        for (Object[] result : results) {
            ApartmentCategoryQuantity acq = (ApartmentCategoryQuantity) result[0];
            Cost cost = (Cost) result[1];
            BigDecimal totalCost = (BigDecimal) result[2];

            quantitesAndCostsById.computeIfAbsent(acq, k -> new HashMap<>())
                    .put(cost, totalCost);
        }
        return quantitesAndCostsById;
    }
}
