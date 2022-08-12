package com.example.task.adapter.persistence.security;

import com.example.task.domain.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleJpaRepository extends JpaRepository<Role, Long> {
    Role findByName(String roleName);
}
