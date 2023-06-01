package bankApp.DTOs;

import java.util.UUID;

public record DeleteUserDTO(UUID id, String password) {
}
