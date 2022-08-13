package com.example.task.app.impl.security;

import com.example.task.exception.AuthenticationException;
import com.example.task.service.LoginAttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationFailureListener implements
        ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
    private final LoginAttemptService loginAttemptService;

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent e) {
        String name = (String) e.getAuthentication().getPrincipal();
        loginAttemptService.loginFailed(name);
        throw new AuthenticationException("Неверное имя пользователя или пароль");
    }
}