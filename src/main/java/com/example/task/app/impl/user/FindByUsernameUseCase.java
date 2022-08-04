package com.example.task.app.impl.user;

import com.example.task.app.api.user.FindByUsernameInbound;
import com.example.task.app.api.user.UserRepository;
import com.example.task.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class FindByUsernameUseCase implements FindByUsernameInbound {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public User execute(String username) {
        return userRepository.findByUsername(username);
    }
}
