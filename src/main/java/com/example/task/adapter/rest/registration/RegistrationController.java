package com.example.task.adapter.rest.registration;

import com.example.task.app.api.user.FindByUsernameInbound;
import com.example.task.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final FindByUsernameInbound findByUsernameInbound;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        if (findByUsernameInbound.execute(user.getUsername()) != null) {
            model.put("message", "User exists");
            return "registration";
        }
        return "main";
    }
}
