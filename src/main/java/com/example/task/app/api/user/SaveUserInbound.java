package com.example.task.app.api.user;

import com.example.task.domain.user.User;

public interface SaveUserInbound {
    void execute(User user);
}
