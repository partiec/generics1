package com.example.generics1.controller;

import com.example.generics1.model.GenericEntity;
import com.example.generics1.repository.GenericRepository;
import com.example.generics1.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class GenericController<CatType extends GenericEntity<CatType>> {

    protected final GenericService<CatType> service;

    public GenericController(GenericRepository<CatType> repository) {
        service = new GenericService<CatType>(repository) {
        };
    }

    @GetMapping("")
    public ResponseEntity<Page<CatType>> getPage(Pageable pageable) {
        return ResponseEntity.ok(service.getPage(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatType> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @PutMapping("")
    public ResponseEntity<CatType> update(@RequestBody CatType updated) {
        return ResponseEntity.ok(service.update(updated));
    }

    @PostMapping("")
    public ResponseEntity<CatType> create(@RequestBody CatType created) {
        return ResponseEntity.ok(service.create(created));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Ok");
    }
}
