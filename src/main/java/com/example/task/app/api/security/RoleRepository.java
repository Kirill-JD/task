package com.example.task.app.api.security;

import com.example.task.domain.security.Role;

public interface RoleRepository {

    Role findByName(String roleName);
}
