package bankApp.services;

import bankApp.DTOs.UserOptionsDTO;
import bankApp.entities.UserOptions;
import bankApp.repositories.UserOptionsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserOptionsService {

    private final UserOptionsRepository userOptionsRepository;

    public UserOptionsService(UserOptionsRepository userOptionsRepository) {
        this.userOptionsRepository = userOptionsRepository;
    }

    public UserOptions createUserOptions(UserOptions userOptions) {
        return userOptionsRepository.save(userOptions);
    }

    public Optional<UserOptions> getUserOptionsById(UUID id) {
        return userOptionsRepository.findById(id);
    }

    public List<UserOptions> getAllUserOptions() {
        return userOptionsRepository.findAll();
    }

    public void deleteUserOptionsById(UUID id) {
        userOptionsRepository.deleteById(id);
    }

    public UserOptions updateUserOptions(UserOptions userOptions) {
        return userOptionsRepository.save(userOptions);
    }

    public UserOptionsDTO convertUserOptionsToDTO(UserOptions userOptions) {
        return new UserOptionsDTO(userOptions.isEmailSubscription());
    }

}
