package com.example.task.service;

import com.example.task.adapter.rest.security.dto.LoginFormDto;
import com.example.task.app.impl.security.jwt.JwtTokenProvider;
import com.example.task.exception.ExceedingTheAuthorizationLimitException;
import com.example.task.exception.UsernameInUseException;
import com.example.task.app.api.security.FindByRoleNameInbound;
import com.example.task.app.api.user.FindByUsernameInbound;
import com.example.task.app.api.user.SaveUserInbound;
import com.example.task.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService{
    private final PasswordEncoder passwordEncoder;
    private final FindByUsernameInbound findByUsernameInbound;
    private final SaveUserInbound addUserInbound;
    private final FindByRoleNameInbound findByRoleNameInbound;
    private final LoginAttemptService loginAttemptService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;


    public Map<Object, Object> login(LoginFormDto loginFormDto) throws ExceedingTheAuthorizationLimitException, UsernameNotFoundException {
        String username = loginFormDto.getUsername();
        if (loginAttemptService.isBlocked(username)) {
            throw new ExceedingTheAuthorizationLimitException("Превышено количество попыток авторизации");
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, loginFormDto.getPassword()));
        User user = findByUsernameInbound.execute(username);

        if(user == null) {
            loginAttemptService.loginFailed(username);
            throw new UsernameNotFoundException("Неверное имя пользователя или пароль");
        }

        String token = jwtTokenProvider.createToken(username, user.getRoles());
        Map<Object, Object> response = new HashMap<>();
        response.put("username", username);
        response.put("token", token);
        loginAttemptService.loginSucceeded(username);
        return response;
    }
    public void addUser(User user) throws UsernameInUseException {
        User userFromDb = findByUsernameInbound.execute(user.getUsername());

        if (userFromDb != null) {
            throw new UsernameInUseException("Пользователь с таким именем уже существует");
        }
        user.setRoles(Set.of(findByRoleNameInbound.execute("ROLE_USER")));
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        addUserInbound.execute(user);
    }
}