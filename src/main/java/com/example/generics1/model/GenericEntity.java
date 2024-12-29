package com.example.generics1.model;

/*
    У интерфейса для Entity дженерик - просто <тип>.
    Любой, кто реализует GenericEntity<с типом самого себя > -
    сам станет таким<с типом самого себя>.
*/
public interface GenericEntity<C> {

    void update(C source);

    Long getId();

    C createNewInstance();
}
