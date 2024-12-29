package com.example.generics1.model.tiger;

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
    Tiger реализует GenericEntity<с типом самого себя> и сам становится
    GenericEntity<Tiger>.
 */
public class Tiger implements Serializable, GenericEntity<Tiger> {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer age;
    private String description;
    @CreationTimestamp
    private Date birthday;

    @Override
    public void update(Tiger source) {
        this.name = source.getName();
        this.age = source.getAge();
        this.description = source.getDescription();
        this.birthday = source.getBirthday();
    }


//    public Long getId() {
//        return this.id;
//    }


    @Override
    public Tiger createNewInstance() {
        Tiger newTiger = new Tiger();
        newTiger.update(this);
        return newTiger;
    }


}
