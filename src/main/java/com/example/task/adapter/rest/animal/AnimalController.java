package com.example.task.adapter.rest.animal;

import com.example.task.app.api.animal.DeleteAnimalInbound;
import com.example.task.app.api.animal.FindByIdAnimalInbound;
import com.example.task.app.api.animal.GetListAnimalsByUserIdInbound;
import com.example.task.app.api.animal.SaveAnimalInbound;
import com.example.task.domain.animal.Animal;
import com.example.task.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("animal")
@RequiredArgsConstructor
public class AnimalController {
    private final GetListAnimalsByUserIdInbound getListAnimalsInbound;
    private final SaveAnimalInbound saveAnimalInbound;
    private final FindByIdAnimalInbound findByIdAnimalInbound;
    private final DeleteAnimalInbound deleteAnimalInbound;

    @GetMapping
    public List<Animal> list(@AuthenticationPrincipal User user) {
        return getListAnimalsInbound.execute(user.getId());
    }

    @GetMapping("{id}")
    public Animal getOne(@PathVariable("id") Animal animal) {
        return animal;
    }

    @PostMapping
    public void create(@RequestBody Animal animal) {
        saveAnimalInbound.execute(animal);
    }

    @PutMapping("{id}")
    public void update(@PathVariable("id") Animal animalFromDb, @RequestBody Animal animal) {
        BeanUtils.copyProperties(animal, animalFromDb, "id");
        saveAnimalInbound.execute(animalFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Animal animal){
        deleteAnimalInbound.execute(animal);
    }
}