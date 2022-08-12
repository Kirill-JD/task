package com.example.task.service.security;

import com.example.task.app.api.user.FindByUsernameInbound;
import com.example.task.app.impl.security.jwt.JwtUserFactory;
import com.example.task.domain.security.JwtUser;
import com.example.task.domain.user.User;
import com.example.task.service.LoginAttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final FindByUsernameInbound findByUsernameInbound;
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
        return JwtUserFactory.create(user);
    }
}
