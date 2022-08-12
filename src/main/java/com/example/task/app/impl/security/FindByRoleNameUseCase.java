package com.example.task.app.impl.security;

import com.example.task.app.api.security.FindByRoleNameInbound;
import com.example.task.app.api.security.RoleRepository;
import com.example.task.domain.security.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindByRoleNameUseCase implements FindByRoleNameInbound {

    private final RoleRepository roleRepository;

    @Override
    public Role execute(String roleName) {
        return roleRepository.findByName(roleName);
    }
}
