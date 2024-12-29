package com.example.generics1.service;

import com.example.generics1.model.GenericEntity;
import com.example.generics1.repository.GenericRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public abstract class GenericService<C extends GenericEntity<C>> {

    private final GenericRepository<C> repository;


    public GenericService(GenericRepository<C> repository) {
        this.repository = repository;
    }


    public Page<C> getPage(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    public C get(Long id) {
        return this.repository.findById(id).orElseThrow();
    }

    //////////////////////
    public List<C> findAll() {
        return repository.findAll();
    }
    //////////////////////

    @Transactional
    public C update(C updatedC) {
        C dbC = this.get(updatedC.getId());
        dbC.update(updatedC);
        return this.repository.save(dbC);
    }

    @Transactional
    public C create(C newDomain) {
        C dbC = this.get(newDomain.getId());
        return this.repository.save(dbC);
    }

    @Transactional
    public void delete(Long id) {
        get(id); // проверка есть ли котик с таким id
        this.repository.deleteById(id);
    }
}
