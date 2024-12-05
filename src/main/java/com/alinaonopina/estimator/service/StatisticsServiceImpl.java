package com.alinaonopina.estimator.service;

import com.alinaonopina.estimator.dao.StatisticsDao;
import com.alinaonopina.estimator.entity.Apartment;
import com.alinaonopina.estimator.entity.ApartmentCategoryQuantity;
import com.alinaonopina.estimator.entity.Category;
import com.alinaonopina.estimator.entity.Cost;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StatisticsServiceImpl implements StatisticsService{

    @Autowired
    private StatisticsDao statisticsDao;

    @Override
    @Transactional
    public Map<ApartmentCategoryQuantity, Map<Cost, BigDecimal>> getAllQuantitiesCostsAndTotalCosts() {
        return statisticsDao.getAllQuantitiesCostsAndTotalCosts();
    }

    //расчет итоговых сметных цен в разрезе всех объектов
    @Override
    @Transactional
    public Map<Apartment, BigDecimal> calculateTotalCostsPerApartments() {
        Map<ApartmentCategoryQuantity, Map<Cost, BigDecimal>> allQuantitiesCostsAndTotalCosts =
                getAllQuantitiesCostsAndTotalCosts();

        Map<Apartment, BigDecimal> totalCostsPerApartments =
                allQuantitiesCostsAndTotalCosts.entrySet().stream()
                        .collect(Collectors.groupingBy(
                                entry -> entry.getKey().getApartment(), //получаем Apartment из ApartmentCategoryQuantity
                                Collectors.reducing(
                                        BigDecimal.ZERO,
                                        entry -> entry.getValue().values().stream()
                                                .reduce(BigDecimal.ZERO, BigDecimal::add), //суммируем BigDecimal во вложенной map
                                        BigDecimal::add //суммируем значения по разным ApartmentCategoryQuantity для одного Apartment
                                )
                        ));
        return totalCostsPerApartments;
    }

    //расчет общей стоимости работ по всем объектам
    //(результат всегда равен результату метода calculateOverallTotalCostPerWorkTitles())
    @Override
    @Transactional
    public BigDecimal calculateOverallTotalCostPerPerApartments(Map<Apartment, BigDecimal> totalCostsPerApartments) {
        return totalCostsPerApartments.values().stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    @Transactional
    public Map<Cost, Map<ApartmentCategoryQuantity, BigDecimal>> getAllCostsQuantitiesAndTotalCosts() {
        return statisticsDao.getAllCostsQuantitiesAndTotalCosts();
    }

    //расчет итоговых цен в разрезе всех видов работ и общего кол-ва по каждому виду работы
    @Override
    @Transactional
    public Map<Pair<String, Category>, Pair<Integer, BigDecimal>> calculateTotalCostsPerWorkTitles() {
        Map<Cost, Map<ApartmentCategoryQuantity, BigDecimal>> allCostsQuantitiesAndTotalCosts =
                getAllCostsQuantitiesAndTotalCosts();

        Map<Pair<String, Category>, Pair<Integer, BigDecimal>> totalCostsPerWorkTitles =
                allCostsQuantitiesAndTotalCosts.entrySet().stream()
                        .collect(Collectors.groupingBy(
                                entry -> new Pair<>(entry.getKey().getWorkTitle(),
                                        entry.getKey().getCategory()),  //используем Pair(workTitle, category) в качестве ключа
                                LinkedHashMap::new,  //используем LinkedHashMap для сохранения порядка
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        entries -> {
                                            int totalQuantity = entries.stream()
                                                    .flatMap(e -> e.getValue().keySet().stream())
                                                    .mapToInt(ApartmentCategoryQuantity::getQuantity)
                                                    .sum();

                                            BigDecimal totalCost = entries.stream()
                                                    .flatMap(e -> e.getValue().values().stream())
                                                    .reduce(BigDecimal.ZERO, BigDecimal::add);

                                            return new Pair<>(totalQuantity, totalCost);
                                        }
                                )
                        ));
        return totalCostsPerWorkTitles;
    }

    //расчет общей стоимости по всем видам работ
    //(результат всегда равен результату метода calculateOverallTotalCostPerPerApartments())
    @Override
    @Transactional
    public BigDecimal calculateOverallTotalCostPerWorkTitles(
            Map<Pair<String, Category>, Pair<Integer, BigDecimal>> totalCostsPerWorkTitles) {
        return totalCostsPerWorkTitles.values().stream()
                .map(Pair::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    @Transactional
    public List<Cost> getAllLatestCosts() {
        List<Cost> allCosts = statisticsDao.getAllCosts();

        List<Cost> allLatestCosts = allCosts.stream()
                //группируем по workTitle
                .collect(Collectors.groupingBy(
                        Cost::getWorkTitle,
                        //для каждой группы выбираем элемент с последней датой
                        Collectors.maxBy(Comparator.comparing(Cost::getCostDate))
                ))
                //преобразуем в Stream<Optional<Cost>>, фильтруем непустые Optional, извлекаем Cost
                .values().stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .sorted()
                .collect(Collectors.toList());

        return allLatestCosts;
    }

    @Override
    @Transactional
    public Map<String, Map<Date, BigDecimal>> getAllWorkTitlesDatesAndCostValues() {
        List<Cost> allCosts = statisticsDao.getAllCosts();

        Map<String, Map<Date, BigDecimal>> allWorkTitlesDatesAndCostValues =
                allCosts.stream()
                .collect(Collectors.groupingBy(
                        Cost::getWorkTitle,   //группируем по workTitle
                        Collectors.toMap(
                                //конвертируем LocalDateTime в Date (чтобы использовать в JSTL)
                                cost -> Date.from(cost.getCostDate().atZone(ZoneId.systemDefault()).toInstant()),
                                Cost::getCostValue,
                                (costValue1, costValue2) -> costValue1,
                                //сортируем по дате в обратном порядке
                                () -> new TreeMap<Date, BigDecimal>(Comparator.reverseOrder())
                        )
                ));

        return allWorkTitlesDatesAndCostValues;
    }
}
