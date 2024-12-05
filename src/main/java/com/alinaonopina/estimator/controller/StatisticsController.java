package com.alinaonopina.estimator.controller;

import com.alinaonopina.estimator.entity.Apartment;
import com.alinaonopina.estimator.entity.ApartmentCategoryQuantity;
import com.alinaonopina.estimator.entity.Category;
import com.alinaonopina.estimator.entity.Cost;
import com.alinaonopina.estimator.service.StatisticsService;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/showStatistics")
    public String showStatistics(){
        return "statistics-actions";
    }

    //вывод Итоговых цен в разрезе объектов
    @GetMapping("/showTotalCostsPerApartments")
    public String showTotalCostsPerApartments(Model model){
        Map<ApartmentCategoryQuantity, Map<Cost, BigDecimal>> allQuantitiesCostsAndTotalCosts =
                statisticsService.getAllQuantitiesCostsAndTotalCosts();

        model.addAttribute("allQuantitiesCostsAndTotalCosts", allQuantitiesCostsAndTotalCosts);

        Map<Apartment, BigDecimal> totalCostsPerApartments =
                statisticsService.calculateTotalCostsPerApartments();

        model.addAttribute("totalCostsPerApartments", totalCostsPerApartments);

        BigDecimal totalCost = statisticsService
                .calculateOverallTotalCostPerPerApartments(totalCostsPerApartments);

        model.addAttribute("totalCost", totalCost);

        return "total-costs-per-apartments";
    }

    //вывод Итоговых цен в разрезе видов работ
    @GetMapping("/showTotalCostsPerWorkTitles")
    public String showTotalCostsPerWorkTitles(Model model){

        Map<Cost, Map<ApartmentCategoryQuantity, BigDecimal>> allCostsQuantitiesAndTotalCosts =
                statisticsService.getAllCostsQuantitiesAndTotalCosts();

        model.addAttribute("allCostsQuantitiesAndTotalCosts", allCostsQuantitiesAndTotalCosts);

        Map<Pair<String, Category>, Pair<Integer, BigDecimal>> totalCostsPerWorkTitles =
                statisticsService.calculateTotalCostsPerWorkTitles();

        model.addAttribute("totalCostsPerWorkTitles", totalCostsPerWorkTitles);

        BigDecimal totalCost = statisticsService
                .calculateOverallTotalCostPerWorkTitles(totalCostsPerWorkTitles);

        model.addAttribute("totalCost", totalCost);

        return "total-costs-per-worktitles";
    }

    //вывод Истории изменения цен
    @GetMapping("/showHistoryOfCosts")
    public String showHistoryOfCosts(Model model){
        List<Cost> allLatestCosts = statisticsService.getAllLatestCosts();

        model.addAttribute("allLatestCosts", allLatestCosts);

        Map<String, Map<Date, BigDecimal>> allWorkTitlesDatesAndCostValues =
                statisticsService.getAllWorkTitlesDatesAndCostValues();

        model.addAttribute("allWorkTitlesDatesAndCostValues", allWorkTitlesDatesAndCostValues);

        return "history-costs";
    }
}
