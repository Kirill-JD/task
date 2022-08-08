package com.example.task.app.impl.animal;

import com.example.task.app.api.animal.AnimalRepository;
import com.example.task.app.api.animal.SaveAnimalInbound;
import com.example.task.domain.animal.Animal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveAnimalUseCase implements SaveAnimalInbound {

    private final AnimalRepository animalRepository;

    @Override
    public void execute(Animal animal) {
        animalRepository.save(animal);
    }
}
