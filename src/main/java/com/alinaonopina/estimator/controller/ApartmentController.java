package com.alinaonopina.estimator.controller;

import com.alinaonopina.estimator.entity.Apartment;
import com.alinaonopina.estimator.entity.Cost;
import com.alinaonopina.estimator.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@Controller
public class ApartmentController {

    @Autowired
    private ApartmentService apartmentService;

    @GetMapping("/showAllApartments")
    public String showAllApartments(Model model){
        List<Apartment> allApartments = apartmentService.getAllApartments();
        model.addAttribute("allApts", allApartments);
        return "all-apartments";
    }

    @GetMapping("/addNewApartment")
    public String addNewApartment(@ModelAttribute("apt") Apartment apartment){

        return "apartment-general-info";
    }

    @PostMapping("/saveApartment")
    public String saveApartment(@Valid @ModelAttribute("apt") Apartment apartment,
                                BindingResult result){

        if (result.hasErrors()) {
            return "apartment-general-info";
        }

        //при обновлении объекта apartment получаем его список цен и назначаем ему заново
        if (apartment.getId() != null){
            int aptId = apartment.getId();
            List<Cost> costs = apartmentService.getApartment(aptId).getCosts();
            apartment.setCosts(costs);
        }

        apartmentService.saveApartment(apartment);

        return "redirect:/showAllApartments";
    }

    @GetMapping("/updateApartment")
    public String updateApartment(@RequestParam("aptId") int aptId, Model model){
        Apartment apartment = apartmentService.getApartment(aptId);

        model.addAttribute("apt", apartment);

        return "apartment-general-info";
    }

    @RequestMapping("/deleteApartment")
    public String deleteApartment(@RequestParam("aptId") int aptId){
        apartmentService.deleteApartment(aptId);

        return "redirect:/showAllApartments";
    }

    @GetMapping("/doActions/{id}")
    public String doActionsWithApartment(@PathVariable("id") int aptId, Model model){
        model.addAttribute("apt", apartmentService.getApartment(aptId));

        return "apartment-actions";
    }
}
