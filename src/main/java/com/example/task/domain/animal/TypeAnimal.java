package com.example.task.domain.animal;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "type_animal")
@Getter
@Setter
public class TypeAnimal implements Serializable {

    @Serial
    private static final long serialVersionUID = -5449326074498337967L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "type_animal_id_generator")
    @SequenceGenerator(name = "type_animal_id_generator", sequenceName = "sq_type_animal_id", allocationSize = 1)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private TypeName name;

}
