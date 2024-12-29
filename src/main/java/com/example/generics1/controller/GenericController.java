package com.example.generics1.controller;

import com.example.generics1.model.GenericEntity;
import com.example.generics1.repository.GenericRepository;
import com.example.generics1.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class GenericController<C extends GenericEntity<C>> {

    protected final GenericService<C> service;

    public GenericController(GenericRepository<C> repository) {
        service = new GenericService<C>(repository) {
        };
    }


    /*
        Каждый метод НАСЛЕДУЕТСЯ контроллером-наследником
        вместе с аннотацией @GetMapping, следовательно, в каждом
        контроллере у методов будут одинаковые КОНЦОВКИ url. Надо
        внимательно проследить, чтобы НАЧАЛА у url были
        РАЗНЫЕ. Если по недосмотру окажется, что url одинаковые, то
        получим ошибку типа:
        There is already 'имяБина' bean method...
        (Этот метод уже принадлежит бину 'такому-то'...)
     */
    @GetMapping("")
    public ResponseEntity<Page<C>> getPage(Pageable pageable) {
        return ResponseEntity.ok(service.getPage(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<C> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @PutMapping("")
    public ResponseEntity<C> update(@RequestBody C updated) {
        return ResponseEntity.ok(service.update(updated));
    }

    @PostMapping("")
    public ResponseEntity<C> create(@RequestBody C created) {
        return ResponseEntity.ok(service.create(created));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Ok");
    }
}
