package com.example.task.app.api.animal;

import com.example.task.domain.animal.Animal;

import java.util.List;

public interface GetListAnimalsByUserIdInbound {
    List<Animal> execute(long id);
}
