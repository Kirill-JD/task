package com.example.task.app.api.security;

import com.example.task.domain.security.Role;

public interface FindByRoleNameInbound {
    Role execute(String roleName);
}
