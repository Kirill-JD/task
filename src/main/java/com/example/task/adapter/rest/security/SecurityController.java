package com.example.task.adapter.rest.security;

import com.example.task.adapter.rest.security.dto.JwtResponse;
import com.example.task.adapter.rest.security.dto.LoginFormDto;
import com.example.task.app.api.security.LoginInbound;
import com.example.task.domain.user.User;
import com.example.task.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class SecurityController {
    private final UserService userService;
    private final LoginInbound loginInbound;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateEmployee(@RequestBody LoginFormDto loginFormDto) {
        String jwtToken = loginInbound.execute(loginFormDto.getLogin(), loginFormDto.getPassword());

        return ResponseEntity.ok(new JwtResponse(jwtToken));
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model, HttpServletRequest request) {
        String username = user.getUsername();
        String userPassword = user.getPassword();

        if (!userService.addUser(user)) {
            model.put("message", "User exists!");
            return "registration";
        }
        try {
            request.login(username, userPassword);
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return "redirect:/main";
    }
}