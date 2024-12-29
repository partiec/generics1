package com.example.generics1.controller;

import com.example.generics1.model.Tiger;
import com.example.generics1.repository.GenericRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/tiger")
public class TigerController extends GenericController<Tiger> {
    public TigerController(GenericRepository<Tiger> tigerRepository) {
        super(tigerRepository);
    }
}
