package com.example.task.adapter.rest.security;

import com.example.task.adapter.rest.security.dto.LoginFormDto;
import com.example.task.app.api.user.FindByUsernameInbound;
import com.example.task.app.impl.security.jwt.JwtTokenProvider;
import com.example.task.domain.user.User;
import com.example.task.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api")
@RequiredArgsConstructor
public class SecurityController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final FindByUsernameInbound findByUsernameInbound;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginFormDto loginFormDto) {
        try {
            String username = loginFormDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, loginFormDto.getPassword()));
            User user = findByUsernameInbound.execute(username);

            if(user == null) {
                throw new UsernameNotFoundException("");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());
            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("");
        }
    }

    @PostMapping("/registration")
    public ResponseEntity addUser(@RequestBody LoginFormDto loginFormDto, HttpServletRequest request) {
        String username = loginFormDto.getUsername();
        String password = loginFormDto.getPassword();

//        if (!userService.addUser(new User(username, password))) {
//            return ResponseEntity.badRequest().body("Имя пользователя уже существует");
//        }
//        try {
//            request.login(username, password);
//        } catch (ServletException e) {
//            e.printStackTrace();
//        }
        return ResponseEntity.ok("ok");
    }
}