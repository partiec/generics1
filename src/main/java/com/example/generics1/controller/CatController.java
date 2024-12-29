package com.example.generics1.controller;

import com.example.generics1.model.Cat;
import com.example.generics1.repository.GenericRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class CatController extends GenericController<Cat> {
    public CatController(GenericRepository<Cat> catRepository) {
        super(catRepository);
    }


    @GetMapping("api/cat")
    public String findAll(Model model) {

        model.addAttribute("allCats", service.findAll());
        return "cats";
    }
}
