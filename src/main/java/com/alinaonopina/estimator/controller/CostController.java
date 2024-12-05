package com.alinaonopina.estimator.controller;

import com.alinaonopina.estimator.entity.Category;
import com.alinaonopina.estimator.entity.Cost;
import com.alinaonopina.estimator.service.CategoryService;
import com.alinaonopina.estimator.service.CostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CostController {

    @Autowired
    private CostService costService;

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/showAllActualCosts")
    public String showAllActualCosts(Model model){
        List<Cost> allActualCosts = costService.getAllActualCosts();
        model.addAttribute("allActualCosts", allActualCosts);

        return "all-actual-costs";
    }

    @GetMapping("/addNewCost")
    public String addNewCost(Model model){
        List<Category> allCategories = categoryService.getAllCategories();
        Cost cost = new Cost();
        model.addAttribute("cost", cost);
        model.addAttribute("allCategories", allCategories);
        return "cost-new";
    }

    @PostMapping("/saveCost")
    public String saveCost(@Valid @ModelAttribute("cost") Cost cost,
                           BindingResult result,
                           Model model) {

        //проверяем наличие ошибок валидации
        if (result.hasErrors() || (cost.getCategory() == null || cost.getCategory().getId() == null)) {
            //проверяем, выбрана ли категория; если нет, добавляем сообщение об ошибке
            if (cost.getCategory() == null || cost.getCategory().getId() == null) {
                model.addAttribute("categoryError", "Пожалуйста, выберите категорию работы");
            }

            //получаем и передаем список всех категорий обратно в модель
            model.addAttribute("allCategories", categoryService.getAllCategories());

            //проверяем, новый ли объект cost, и возвращаем соответствующую страницу
            return cost.getId() == null ? "cost-new" : "cost-change";
        }

        //сохраняем объект cost в БД
        costService.saveCost(cost);

        return "redirect:/showAllActualCosts";
    }

    @GetMapping("/changeCost")
    public String changeCost(@RequestParam("costId") int costId, Model model){
        Cost cost = costService.getCost(costId);
        model.addAttribute("cost", cost);
        return "cost-change";
    }

    @RequestMapping("/deleteCost")
    public String deleteCost(@RequestParam("costId") int costId){
        costService.deactivateCost(costId);

        return "redirect:/showAllActualCosts";
    }
}
