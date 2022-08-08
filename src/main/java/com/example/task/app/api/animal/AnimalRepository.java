package com.example.task.app.api.animal;

import com.example.task.domain.animal.Animal;

import java.util.List;

public interface AnimalRepository {

    Animal findById(long id);

    List<Animal> getListAnimalsByUserIdName(long id);

    void delete(Animal animal);

    void save(Animal animal);
}
