package com.example.generics1.controller;

import com.example.generics1.model.cat.Cat;
import com.example.generics1.repository.GenericRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "api/cat")
public class CatController extends GenericController<Cat> {
    public CatController(GenericRepository<Cat> catRepository) {
        super(catRepository);
    }


    @GetMapping(value = "findAll")
    public String vseKotiki(Model model) {

        model.addAttribute("allCats", service.findAll());
        return "cats";
    }

    ///////////////
    public void testMethod() {

    }
    ///////////////


}
