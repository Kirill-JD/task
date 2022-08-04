package com.example.task.adapter.rest.registration;

import com.example.task.domain.user.User;
import com.example.task.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);
    private final UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model, HttpServletRequest request) {
        String userPassword = user.getPassword();

        if (!userService.addUser(user)) {
            model.put("message", "User exists!");
            return "registration";
        }
        try {
            request.login(user.getUsername(), userPassword);
        } catch (ServletException e) {
            LOGGER.error("Error while login ", e);
        }
        return "redirect:/main";
    }
}
