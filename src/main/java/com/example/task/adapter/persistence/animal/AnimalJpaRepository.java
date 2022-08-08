package com.example.task.adapter.persistence.animal;

import com.example.task.domain.animal.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnimalJpaRepository extends JpaRepository<Animal, Long> {

    Animal findById(long id);
    Animal findByName(String name);

    @Query(value = "SELECT * FROM animal WHERE usr = :id",
            nativeQuery = true)
    List<Animal> getListAnimalsByUserIdName(@Param("id") long id);

}
