package com.example.task.service;

import com.example.task.app.api.user.SaveUserInbound;
import com.example.task.app.api.user.FindByUsernameInbound;
import com.example.task.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final FindByUsernameInbound findByUsernameInbound;
    private final SaveUserInbound addUserInbound;
    private final LoginAttemptService loginAttemptService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsernameInbound.execute(username);

        if (loginAttemptService.isBlocked(username)) {
            throw new RuntimeException("blocked");
        }
        try {
            if (user == null) {
                throw new UsernameNotFoundException("User not found");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
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