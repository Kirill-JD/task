package com.example.task.service;

import com.example.task.app.api.user.AddUserInbound;
import com.example.task.app.api.user.FindByUsernameInbound;
import com.example.task.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final FindByUsernameInbound findByUsernameInbound;
    private final AddUserInbound addUserInbound;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsernameInbound.execute(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public boolean addUser(User user) {
        User userFromDb = findByUsernameInbound.execute(user.getUsername());

        if (userFromDb != null) {
            return false;
        }

        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        addUserInbound.execute(user);

        return true;
    }
}