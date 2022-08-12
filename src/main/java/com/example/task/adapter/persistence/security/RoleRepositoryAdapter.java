package com.example.task.adapter.persistence.security;

import com.example.task.app.api.security.RoleRepository;
import com.example.task.domain.security.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RoleRepositoryAdapter implements RoleRepository {

    private final RoleJpaRepository roleJpaRepository;


    @Override
    public Role findByName(String roleName) {
        return roleJpaRepository.findByName(roleName);
    }
}
