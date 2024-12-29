package com.example.generics1.controller;

import com.example.generics1.model.GenericEntity;
import com.example.generics1.repository.GenericRepository;
import com.example.generics1.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class GenericController<C extends GenericEntity<C>> {

    protected final GenericService<C> service;
    /*
        Инициализируем service в конструкторе, но не как обычно: конструктор
        принимает в качестве аргумента не service, а GenericRepository<C>!
        Этот репозиторий мы пробрасываем конструктору из GenericServise<C>,
        который и создает нам service, при этом проброшенным репозиторием
        инициализируя свою переменную repository.
     */

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
        (Этот метод уже принадлежит бину 'такому-то'...
     */

    ////////////////
    @GetMapping("findAll")
    public String findAll(Model model) {
        List<C> cList = service.findAll();
        String type = cList.get(0).getClass().getSimpleName();
        model.addAttribute("allOf" + type, cList);
        return "cats";
    }

    ////////////////
    @GetMapping("getPage")
    public ResponseEntity<Page<C>> getPage(Pageable pageable) {
        return ResponseEntity.ok(service.getPage(pageable));
    }

    @GetMapping("getOne/{id}")
    public ResponseEntity<C> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @PutMapping("update")
    public ResponseEntity<C> update(@RequestBody C updated) {
        return ResponseEntity.ok(service.update(updated));
    }

    @PostMapping("create")
    public ResponseEntity<C> create(@RequestBody C created) {
        return ResponseEntity.ok(service.create(created));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Ok");
    }
}
