package com.alinaonopina.estimator.dao;

import com.alinaonopina.estimator.entity.Cost;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CostDaoImpl implements CostDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Cost> getAllActualCosts() {   //выводит из БД список актуальных цен (без дубликатов)
        Session session = sessionFactory.getCurrentSession();

        List<Cost> allActualCosts = session
                .createQuery("FROM Cost WHERE isActual=true",
                        Cost.class)
                .getResultList();

        return allActualCosts;
    }

    @Override
    public void saveCost(Cost cost) {
        Session session = sessionFactory.getCurrentSession();
        session.save(cost);
    }

    @Override
    public void deactivateCosts(Cost cost) {          //делает предыдущие значения цены архивными
        Session session = sessionFactory.getCurrentSession();

        Query<Cost> query = session.createQuery("UPDATE Cost " +
                "SET isActual = false WHERE workTitle = :workTitle");

        query.setParameter("workTitle", cost.getWorkTitle());

        query.executeUpdate();
    }

    @Override
    public Cost getCost(int costId) {
        Session session = sessionFactory.getCurrentSession();

        Cost cost = session.get(Cost.class, costId);

        if (cost == null) {
            throw new IllegalArgumentException("Цена с таким id не найдена: " + costId);
        }
        return cost;
    }

    @Override
    public void deactivateCost(int costId) {
        Session session = sessionFactory.getCurrentSession();

        //цену не удаляем из БД, а сбрасываем флаг isActive для текущего значения цены
        Query<Cost> query = session.createQuery("UPDATE Cost " +
                "SET isActual = false WHERE id = :costId");

        query.setParameter("costId", costId);

        query.executeUpdate();
    }

    @Override
    public List<Cost> getCostsByCostsIds(List<Integer> costsIds) {
        Session session = sessionFactory.getCurrentSession();

        String hqlRequest = "FROM Cost AS c WHERE c.id IN :costsIds";

        List<Cost> costs = session
                .createQuery(hqlRequest, Cost.class)
                .setParameter("costsIds", costsIds)
                .getResultList();

        return costs;
    }
}
