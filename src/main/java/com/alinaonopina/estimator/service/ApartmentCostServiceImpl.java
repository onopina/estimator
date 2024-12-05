package com.alinaonopina.estimator.service;

import com.alinaonopina.estimator.dao.ApartmentCostDao;
import com.alinaonopina.estimator.dao.ApartmentDao;
import com.alinaonopina.estimator.dao.CostDao;
import com.alinaonopina.estimator.entity.Apartment;
import com.alinaonopina.estimator.entity.ApartmentCategoryQuantity;
import com.alinaonopina.estimator.entity.Category;
import com.alinaonopina.estimator.entity.Cost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ApartmentCostServiceImpl implements ApartmentCostService{

    @Autowired
    private ApartmentCostDao apartmentCostDao;

    @Autowired
    private CostDao costDao;

    @Autowired
    private ApartmentDao apartmentDao;

    @Override
    @Transactional
    public Map<ApartmentCategoryQuantity, Map<Cost, BigDecimal>> getQuantitiesAndCostsById(int aptId) {
        return apartmentCostDao.getQuantitiesAndCostsById(aptId);
    }

    @Override
    public BigDecimal calculateTotalCostByApartmentId(
            Map<ApartmentCategoryQuantity, Map<Cost, BigDecimal>> quantitiesAndCostsById) {
        return quantitiesAndCostsById.values()
                .stream()
                .flatMap(map -> map.values().stream())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    @Transactional
    public void refreshCostsByApartmentId(int aptId) {
        //шаг 1: получаем объект по id
        Apartment apartment = apartmentDao.getApartment(aptId);

        //шаг 2: получаем список актуальных цен (без вывода дубликатов)
        List<Cost> actualCosts = costDao.getAllActualCosts();

        //шаг 3: обновляем List of costs у объекта
        List<Cost> existingCosts = apartment.getCosts();

        existingCosts.replaceAll(existingCost ->
                        actualCosts.stream()
                                .filter(actualCost -> actualCost.getWorkTitle().equals(existingCost.getWorkTitle()))
                                .findFirst()
                                .orElse(existingCost) //если совпадение не найдено, оставляем старый элемент
                );

        apartment.setCosts(existingCosts);

        //шаг 4: сохраняем изменения в БД
        apartmentDao.saveApartment(apartment);
    }

    @Override
    @Transactional
    public List<Cost> getAvailableCostsByApartmentId(int aptId) {
        //шаг 1: получаем объект apartment по id
        Apartment apartment = apartmentDao.getApartment(aptId);

        //шаг 2: получаем список актуальных цен (без вывода дубликатов)
        List<Cost> actualCosts = costDao.getAllActualCosts();

        //шаг 3: получаем список цен, доступных для добавления к объекту

        //шаг 3.1: получаем список работ, уже заданных для объекта
        Set<String> existingWorkTitles =
                apartment.getCosts()
                        .stream().map(cost -> cost.getWorkTitle())
                        .collect(Collectors.toSet());

        //шаг 3.2: получаем список категорий, уже заданных для объекта
        List<ApartmentCategoryQuantity> apartmentCategoryQuantities = apartment.getApartmentCategoryQuantities();

        Set<Category> apartmentCategories = apartmentCategoryQuantities.stream()
                .map(apartmentCategoryQuantity -> apartmentCategoryQuantity.getCategory())
                .collect(Collectors.toSet());

        //шаг 3.3: фильтруем актуальный список цен (убираем из списка актуальных цен те, работы по которым
        // уже есть у объекта, и оставляем те, у которых категории совпадают с категориями, заданными для объекта)
        List<Cost> availableCosts = actualCosts.stream()
                .filter(cost -> !existingWorkTitles.contains(cost.getWorkTitle())
                        && apartmentCategories.contains(cost.getCategory()))
                .collect(Collectors.toList());

        return availableCosts;
    }

    @Override
    @Transactional
    public void saveNewCostsToApartment(List<Integer> newCostsIds, int aptId) {
        //шаг 1: получаем объект apartment по id и список цен, уже заданных для объекта
        Apartment apartment = apartmentDao.getApartment(aptId);
        List<Cost> existingCosts = apartment.getCosts();

        //шаг 2: получаем список цен для добавления к объекту по списку их id
        List<Cost> newCosts = costDao.getCostsByCostsIds(newCostsIds);

        //шаг 3: добавляем новые цены к текущему списку цен объекта
        existingCosts.addAll(newCosts);
        apartment.setCosts(existingCosts);

        //шаг 4: сохраняем изменения в БД
        apartmentDao.saveApartment(apartment);
    }

    @Override
    @Transactional
    public void deleteCostFromApartment(int aptId, int costId) {
        Apartment apartment = apartmentDao.getApartment(aptId);
        Cost cost = costDao.getCost(costId);

        apartment.getCosts().remove(cost);

        apartmentDao.saveApartment(apartment);
    }
}
