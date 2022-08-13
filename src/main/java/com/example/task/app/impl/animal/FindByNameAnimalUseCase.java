package com.example.task.app.impl.animal;

import com.example.task.app.api.animal.AnimalRepository;
import com.example.task.app.api.animal.FindByNameAnimalInbound;
import com.example.task.domain.animal.Animal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindByNameAnimalUseCase implements FindByNameAnimalInbound {

    private final AnimalRepository animalRepository;

    @Override
    public Animal execute(String name) {
        return animalRepository.findByName(name);
    }
}
