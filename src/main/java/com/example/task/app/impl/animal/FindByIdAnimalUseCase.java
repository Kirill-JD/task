package com.example.task.app.impl.animal;

import com.example.task.app.api.animal.AnimalRepository;
import com.example.task.app.api.animal.FindByIdAnimalInbound;
import com.example.task.domain.animal.Animal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindByIdAnimalUseCase implements FindByIdAnimalInbound {

    private final AnimalRepository animalRepository;

    @Override
    public Animal execute(long id) {
        return animalRepository.findById(id);
    }
}
