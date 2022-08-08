package com.example.task.app.api.animal;

import com.example.task.domain.animal.Animal;

public interface SaveAnimalInbound {
    void execute(Animal animal);
}
