package bankApp.services;

import bankApp.entities.Role;
import bankApp.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RoleService {

    public static final String CLIENT = "client";
    public static final String EMPLOYEE = "employee";
    public static final String ADMIN = "admin";

    private final RoleRepository roleRepository;

    public Optional<Role> getRoleByRole(String name) {
        return roleRepository.findByRole(name);
    }
}
