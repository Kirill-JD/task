package com.example.task.adapter.persistence.user;

import com.example.task.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
