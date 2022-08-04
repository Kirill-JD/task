package com.example.task.adapter.persistence.user;

import com.example.task.app.api.user.UserRepository;
import com.example.task.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {
    private final UserJpaRepository userJpaRepository;

    @Override
    public User findByUsername(String username) {
        return userJpaRepository.findByUsername(username);
    }

    @Override
    public void save(User user) {
        userJpaRepository.save(user);
    }
}
