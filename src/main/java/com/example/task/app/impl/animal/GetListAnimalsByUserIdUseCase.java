package com.example.task.app.impl.animal;

import com.example.task.app.api.animal.AnimalRepository;
import com.example.task.app.api.animal.GetListAnimalsByUserIdInbound;
import com.example.task.domain.animal.Animal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetListAnimalsByUserIdUseCase implements GetListAnimalsByUserIdInbound {

    private final AnimalRepository animalRepository;

    @Override
    public List<Animal> execute(long id) {
        return animalRepository.getListAnimalsByUserIdName(id);
    }
}
