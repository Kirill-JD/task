package com.example.task.adapter.rest.animal;

import com.example.task.app.api.animal.DeleteAnimalInbound;
import com.example.task.app.api.animal.FindByNameAnimalInbound;
import com.example.task.app.api.animal.GetListAnimalsByUserIdInbound;
import com.example.task.app.api.animal.SaveAnimalInbound;
import com.example.task.app.api.user.FindByUsernameInbound;
import com.example.task.domain.animal.Animal;
import com.example.task.domain.animal.GenderAnimal;
import com.example.task.domain.animal.TypeAnimal;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class AnimalController {
    private final GetListAnimalsByUserIdInbound getListAnimalsInbound;
    private final SaveAnimalInbound saveAnimalInbound;
    private final DeleteAnimalInbound deleteAnimalInbound;
    private final FindByUsernameInbound findByUsernameInbound;
    private final FindByNameAnimalInbound findByNameAnimalInbound;

    @GetMapping
    public List<Animal> list(@RequestAttribute String currentUser) {
        return getListAnimalsInbound.execute(findByUsernameInbound.execute(currentUser).getId());
    }

    @GetMapping("{id}")
    public Animal getOne(@PathVariable("id") Animal animal, @RequestAttribute String currentUser) {
        if (animal.getUser() == findByUsernameInbound.execute(currentUser)) {
            return animal;
        } else {
            return null;
        }
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Animal animal) {
        if (findByNameAnimalInbound.execute(animal.getName()) == null) {
            saveAnimalInbound.execute(animal);
            return ResponseEntity.ok("Животное успешно добавлено");
        }
        return ResponseEntity.badRequest().body("Кличка животного уже используется");
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") Animal animalFromDb, @RequestBody Animal animal) {
        if (findByNameAnimalInbound.execute(animalFromDb.getName()) == null) {
            BeanUtils.copyProperties(animal, animalFromDb, "id");
            saveAnimalInbound.execute(animalFromDb);
            return ResponseEntity.ok("Данные животного успещно обновлены");
        }
        return ResponseEntity.badRequest().body("Кличка животного уже используется");
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Animal animal, @RequestAttribute String currentUser){
        if (animal.getUser() == findByUsernameInbound.execute(currentUser)) {
            deleteAnimalInbound.execute(animal);
            return ResponseEntity.ok("Животное успешно удалено");
        }
        return ResponseEntity.badRequest().body("Животное не найдено");
    }

    @GetMapping("/gender_animal")
    public Set<String> genderAnimal() {
        return Stream.of(GenderAnimal.values()).map(GenderAnimal::name).collect(Collectors.toSet());
    }

    @GetMapping("/type_animal")
    public Set<String> typeAnimal() {
        return Stream.of(TypeAnimal.values()).map(TypeAnimal::name).collect(Collectors.toSet());
    }
}