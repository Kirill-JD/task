package com.example.task.app.impl.animal;

import com.example.task.app.api.animal.AnimalRepository;
import com.example.task.app.api.animal.DeleteAnimalInbound;
import com.example.task.domain.animal.Animal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteAnimalUseCase implements DeleteAnimalInbound {

    private final AnimalRepository animalRepository;

    @Override
    public void execute(Animal animal) {
        animalRepository.delete(animal);
    }
}
