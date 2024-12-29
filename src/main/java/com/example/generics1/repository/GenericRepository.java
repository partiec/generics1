package com.example.generics1.repository;

import com.example.generics1.model.GenericEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
/*
    В @NoRepositoryBean-репозитории указываем <типКота - extends свою же абстракцию<со своим же типом>>. Хотя абстракция для котов - интерфейс, все равно extends !!!
 */
public interface GenericRepository<C extends GenericEntity<C>>
        extends JpaRepository<C, Long>, JpaSpecificationExecutor<C> {
}
