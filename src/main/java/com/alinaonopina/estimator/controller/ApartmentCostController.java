package com.alinaonopina.estimator.controller;

import com.alinaonopina.estimator.entity.Apartment;
import com.alinaonopina.estimator.entity.ApartmentCategoryQuantity;
import com.alinaonopina.estimator.entity.Cost;
import com.alinaonopina.estimator.service.ApartmentCostService;
import com.alinaonopina.estimator.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.util.*;

@Controller
public class ApartmentCostController {

    @Autowired
    private ApartmentCostService apartmentCostService;

    @Autowired
    private ApartmentService apartmentService;

    @GetMapping("/quantitiesAndCostsById/{id}")
    public String showQuantitiesAndCostsById(@PathVariable("id") int aptId,
                                             @RequestParam(required = false) String refreshInfo,
                                             @RequestParam(required = false) BigDecimal previousCost,
                                             Model model){
        Map<ApartmentCategoryQuantity, Map<Cost, BigDecimal>> quantitiesAndCostsById =
                apartmentCostService.getQuantitiesAndCostsById(aptId);

        model.addAttribute("quantitiesAndCosts", quantitiesAndCostsById);

        BigDecimal totalCost = apartmentCostService.calculateTotalCostByApartmentId(quantitiesAndCostsById);

        model.addAttribute("totalCost", totalCost);
        model.addAttribute("apt", apartmentService.getApartment(aptId));

        if (totalCost.equals(previousCost)){
            refreshInfo = null;
        }

        model.addAttribute("refreshInfo", refreshInfo);
        return "apartment-quantities-costs";
    }

    @GetMapping("/refreshCosts/{id}")
    public String refreshCostsByApartmentId(@PathVariable("id") int aptId,
                                            @RequestParam("totalCost") BigDecimal totalCost,
                                            Model model){

        apartmentCostService.refreshCostsByApartmentId(aptId);

        String refreshInfo = "Цены успешно пересчитаны";

        model.addAttribute("previousCost", totalCost);
        model.addAttribute("refreshInfo", refreshInfo);

        return "redirect:/quantitiesAndCostsById/" + aptId;
    }

    @GetMapping("/addNewCostsToApartment/{id}")
    public String addNewCostsToApartment(@PathVariable("id") int aptId, Model model) {
        Apartment apartment = apartmentService.getApartment(aptId);
        model.addAttribute("apt", apartment);

        List<Cost> availableCosts = apartmentCostService.getAvailableCostsByApartmentId(aptId);

        model.addAttribute("availableCosts", availableCosts);

        model.addAttribute("selectedCosts", new SelectedCosts());

        return "apartment-new-costs";
    }

    @PostMapping("/saveNewCostsToApartment/{id}")
    public String saveNewCostsToApartment(@PathVariable("id") int aptId,
                                          @ModelAttribute("selectedCosts") SelectedCosts selectedCosts) {

        List<Integer> selectedCostIds = selectedCosts.getSelectedCostIds();
        apartmentCostService.saveNewCostsToApartment(selectedCostIds, aptId);

        return "redirect:/quantitiesAndCostsById/" + aptId;
    }

    @RequestMapping("/deleteCostFromApartment/{id}")
    public String deleteCostFromApartment(@PathVariable("id") int aptId,
                                          @RequestParam("costId") int costId){
        apartmentCostService.deleteCostFromApartment(aptId, costId);

        return "redirect:/quantitiesAndCostsById/" + aptId;
    }

    //временный класс для хранения выбранных идентификаторов
    //(используется для получения выбранных id из jsp-формы)
    public class SelectedCosts {
        private List<Integer> selectedCostIds = new ArrayList<>();

        public List<Integer> getSelectedCostIds() {
            return selectedCostIds;
        }

        public void setSelectedCostIds(List<Integer> selectedCostIds) {
            this.selectedCostIds = selectedCostIds;
        }
    }
}
