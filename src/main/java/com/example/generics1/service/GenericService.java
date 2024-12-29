package com.example.generics1.service;

import com.example.generics1.model.GenericEntity;
import com.example.generics1.repository.GenericRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public abstract class GenericService<CatType extends GenericEntity<CatType>> {

    private final GenericRepository<CatType> repository;


    public GenericService(GenericRepository<CatType> repository) {
        this.repository = repository;
    }


    public Page<CatType> getPage(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    public CatType get(Long id) {
        return this.repository.findById(id).orElseThrow();
    }

    //////////////////////
    public List<CatType> findAll() {
        return repository.findAll();
    }
    //////////////////////

    @Transactional
    public CatType update(CatType updatedCatType) {
        CatType dbCatType = this.get(updatedCatType.getId());
        dbCatType.update(updatedCatType);
        return this.repository.save(dbCatType);
    }

    @Transactional
    public CatType create(CatType newDomain) {
        CatType dbCatType = this.get(newDomain.getId());
        return this.repository.save(dbCatType);
    }

    @Transactional
    public void delete(Long id) {
        get(id); // проверка есть ли котик с таким id
        this.repository.deleteById(id);
    }
}
