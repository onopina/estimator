package com.alinaonopina.estimator.controller;

import com.alinaonopina.estimator.entity.ApartmentCategoryQuantity;
import com.alinaonopina.estimator.entity.Category;
import com.alinaonopina.estimator.service.ApartmentCategoryQuantityService;
import com.alinaonopina.estimator.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ApartmentCategoryQuantityController {

    @Autowired
    private ApartmentCategoryQuantityService apartmentCategoryQuantityService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categoriesAndQuantitiesById/{id}")
    public String showCategoriesAndQuantitiesById(@PathVariable("id") int aptId, Model model) {
        List<ApartmentCategoryQuantity> categoriesAndQuantitiesById =
                apartmentCategoryQuantityService.getCategoriesAndQuantitiesById(aptId);
        model.addAttribute("categoriesAndQuantitiesById", categoriesAndQuantitiesById);
        model.addAttribute("apt", apartmentCategoryQuantityService.getApartmentById(aptId));
        return "apartment-quantities";
    }

    @GetMapping("/addNewCategoryQuantity/{id}")
    public String addNewCategoryQuantity(@PathVariable("id") int id,
                                         Model model) {
        List<Category> emptyCategoriesById = categoryService.getEmptyCategoriesById(id);

        model.addAttribute("emptyCategoriesById", emptyCategoriesById);

        ApartmentCategoryQuantity apartmentCategoryQuantity = new ApartmentCategoryQuantity();

        model.addAttribute("acq", apartmentCategoryQuantity);

        model.addAttribute("aptId", id);

        return "apartment-new-quantity";
    }

    @PostMapping("/saveCategoryQuantity/{id}")
    public String saveCategoryQuantity(@PathVariable("id") int aptId
            , @Valid @ModelAttribute("acq") ApartmentCategoryQuantity apartmentCategoryQuantity,
                                       BindingResult result,
                                       Model model) {
        if (result.hasErrors() || apartmentCategoryQuantity.getCategory().getId() == null) {
            if (apartmentCategoryQuantity.getCategory().getId() == null){
                model.addAttribute("categoryError", "Пожалуйста, выберите категорию работы");
            }

            //получаем и передаем список всех доступных категорий обратно в модель
            model.addAttribute("emptyCategoriesById", categoryService.getEmptyCategoriesById(aptId));
            //передаем значение aptId обратно в модель
            model.addAttribute("aptId", aptId);

            return "apartment-new-quantity"; //возвращаем на ту же страницу, чтобы отобразить ошибки
        }

        apartmentCategoryQuantityService.saveApartmentCategoryQuantity(apartmentCategoryQuantity);

        return "redirect:/categoriesAndQuantitiesById/" + aptId;
    }

    @PostMapping("/updateCategoryQuantity/{id}")
    public String updateCategoryQuantity(@PathVariable("id") int aptId
            , @Valid @ModelAttribute("acq") ApartmentCategoryQuantity apartmentCategoryQuantity,
                                         BindingResult result,
                                         Model model){
        if (result.hasErrors()) {

            //назначаем значение category объекта acq и передаем его обратно в модель
            int categoryId = apartmentCategoryQuantity.getCategory().getId();
            apartmentCategoryQuantity.setCategory(categoryService.getCategory(categoryId));

            //передаем значение aptId обратно в модель
            model.addAttribute("aptId", aptId);

            return "apartment-edit-quantity"; //возвращаем на ту же страницу, чтобы отобразить ошибки
        }

        apartmentCategoryQuantity.setId();   //для корректного отрабатывания session.saveOrUpdate()

        apartmentCategoryQuantityService.saveApartmentCategoryQuantity(apartmentCategoryQuantity);

        return "redirect:/categoriesAndQuantitiesById/" + aptId;
    }

    @GetMapping("/editCategoryQuantity/{id}")
    public String editCategoryQuantity(
            @PathVariable("id") int aptId,
            @RequestParam("categoryId") int categoryId,
            Model model) {

        ApartmentCategoryQuantity apartmentCategoryQuantity =
                apartmentCategoryQuantityService.getApartmentCategoryQuantity(aptId, categoryId);

        model.addAttribute("acq", apartmentCategoryQuantity);
        model.addAttribute("aptId", aptId);

        return "apartment-edit-quantity";
    }

    @RequestMapping("/deleteCategoryQuantity/{id}")
    public String deleteCategoryQuantity(
            @PathVariable("id") int aptId,
            @RequestParam("categoryId") int categoryId) {
        apartmentCategoryQuantityService.deleteApartmentCategoryQuantity(aptId, categoryId);

        return "redirect:/categoriesAndQuantitiesById/" + aptId;
    }
}
