package com.example.task.app.api.animal;

import com.example.task.domain.animal.Animal;

public interface DeleteAnimalInbound {
    void execute(Animal animal);
}
