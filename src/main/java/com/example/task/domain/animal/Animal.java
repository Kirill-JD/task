package com.example.task.domain.animal;

import com.example.task.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "animal")
@Getter
@Setter
@NoArgsConstructor
public class Animal implements Serializable {

    public Animal(String name, String birthdate, TypeAnimal typeAnimal, GenderAnimal gender, User user) {
        this.name = name;
        this.birthdate = birthdate;
        this.typeAnimal = typeAnimal;
        this.gender = gender;
        this.user = user;
    }

    @Serial
    private static final long serialVersionUID = -5449326074498337967L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "animal_id_generator")
    @SequenceGenerator(name = "animal_id_generator", sequenceName = "sq_animal_id", allocationSize = 1)
    private Long id;

    private String name;
    private String birthdate;

    @Enumerated(value = EnumType.STRING)
    private TypeAnimal typeAnimal;

    @Enumerated(value = EnumType.STRING)
    private GenderAnimal gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usr")
    @JsonIgnore
    private User user;

}