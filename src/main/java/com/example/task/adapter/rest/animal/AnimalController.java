package com.example.task.adapter.rest.animal;

import com.example.task.adapter.persistence.animal.AnimalJpaRepository;
import com.example.task.app.api.animal.*;
import com.example.task.domain.animal.Animal;
import com.example.task.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api")
@RequiredArgsConstructor
public class AnimalController {
    private final GetListAnimalsByUserIdInbound getListAnimalsInbound;
    private final SaveAnimalInbound saveAnimalInbound;
    private final FindByIdAnimalInbound findByIdAnimalInbound;
    private final DeleteAnimalInbound deleteAnimalInbound;
    private final AnimalRepository animalRepository;

    @GetMapping
    public List<Animal> list() { //@AuthenticationPrincipal User user
        return animalRepository.findAll();
        //return getListAnimalsInbound.execute(user.getId());
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