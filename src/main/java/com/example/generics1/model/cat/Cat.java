package com.example.generics1.model.cat;

import com.example.generics1.model.GenericEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;

@Entity
@NoArgsConstructor
@Data
/*
    Cat реализует GenericEntity<с типом самого себя> и
    становится GenericEntity<Cat>.
 */
public class Cat implements Serializable, GenericEntity<Cat> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer age;
    private String description;
    @CreationTimestamp
    private Date birthday;


    @Override
    public void update(Cat source) {
        this.name = source.getName();
        this.age = source.getAge();
        this.description = source.getDescription();
        this.birthday = source.getBirthday();
    }

//    public Long getId() {
//        return this.id;
//    }

    @Override
    public Cat createNewInstance() {
        Cat newCat = new Cat();
        newCat.update(this);
        return newCat;
    }


}
