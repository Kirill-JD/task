package com.example.task.adapter.rest.security;

import com.example.task.adapter.rest.security.dto.LoginFormDto;
import com.example.task.domain.user.User;
import com.example.task.exception.UsernameInUseException;
import com.example.task.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api")
@RequiredArgsConstructor
public class SecurityController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginFormDto loginFormDto) {
        try {
            return ResponseEntity.ok(userService.login(loginFormDto));
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PostMapping("/registration")
    public ResponseEntity addUser(@RequestBody LoginFormDto loginFormDto) {
        String username = loginFormDto.getUsername();
        String password = loginFormDto.getPassword();

        try {
            userService.addUser(new User(username, password));
        } catch (UsernameInUseException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
        return login(loginFormDto);
    }
}