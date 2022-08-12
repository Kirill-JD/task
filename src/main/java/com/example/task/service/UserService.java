package com.example.task.service;

import com.example.task.exception.UsernameInUseException;
import com.example.task.app.api.security.FindByRoleNameInbound;
import com.example.task.app.api.user.FindByUsernameInbound;
import com.example.task.app.api.user.SaveUserInbound;
import com.example.task.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService{
    private final PasswordEncoder passwordEncoder;
    private final FindByUsernameInbound findByUsernameInbound;
    private final SaveUserInbound addUserInbound;
    private final FindByRoleNameInbound findByRoleNameInbound;

    public void addUser(User user) throws UsernameInUseException {
        User userFromDb = findByUsernameInbound.execute(user.getUsername());

        if (userFromDb != null) {
            throw new UsernameInUseException();
        }
        user.setRoles(Set.of(findByRoleNameInbound.execute("ROLE_USER")));
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        addUserInbound.execute(user);
    }
}