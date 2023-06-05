package bankApp.services;

import bankApp.entities.Role;
import bankApp.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Optional<Role> getRoleByRole(String name) {
        return roleRepository.findByRole(name);
    }
}
