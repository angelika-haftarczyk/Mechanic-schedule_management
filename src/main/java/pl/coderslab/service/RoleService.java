package pl.coderslab.service;

import pl.coderslab.model.Role;

public interface RoleService {
    Role findByName(String name);
    Role save(Role role);
}