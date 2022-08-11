package com.example.task.adapter.persistence.animal;

import com.example.task.app.api.animal.AnimalRepository;
import com.example.task.domain.animal.Animal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AnimalRepositoryAdapter implements AnimalRepository {

    private final AnimalJpaRepository animalJpaRepository;

    @Override
    public Animal findById(long id) {
        return animalJpaRepository.findById(id);
    }

    @Override
    public List<Animal> getListAnimalsByUserIdName(long id) {
        return animalJpaRepository.getListAnimalsByUserIdName(id);
    }

    @Override
    public void delete(Animal animal) {
        animalJpaRepository.delete(animal);
    }

    @Override
    public void save(Animal animal) {
        animalJpaRepository.save(animal);
    }

    @Override
    public List<Animal> findAll() {
        return animalJpaRepository.findAll();
    }

}
