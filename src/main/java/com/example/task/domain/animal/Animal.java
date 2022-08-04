package com.example.task.domain.animal;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "animal")
@Getter
@Setter
public class Animal implements Serializable {

    @Serial
    private static final long serialVersionUID = -5449326074498337967L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "animal_id_generator")
    @SequenceGenerator(name = "animal_id_generator", sequenceName = "sq_animal_id", allocationSize = 1)
    private Long id;

    private String name;
    private String birthdate;

    @ManyToOne
    @JoinColumn(name = "type_animal_id")
    private TypeAnimal typeAnimal;

    @Enumerated(value = EnumType.STRING)
    private GenderAnimal gender;

}