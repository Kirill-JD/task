package com.example.task.app.impl.user;

import com.example.task.app.api.user.AddUserInbound;
import com.example.task.app.api.user.UserRepository;
import com.example.task.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddUserUseCase implements AddUserInbound {

    private final UserRepository userRepository;
    @Override
    public void execute(User user) {
        userRepository.save(user);
    }
}
