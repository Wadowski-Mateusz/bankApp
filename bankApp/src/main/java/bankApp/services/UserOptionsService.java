package bankApp.services;

import bankApp.DTOs.UserOptionsDTO;
import bankApp.entities.UserOptions;
import bankApp.repositories.UserOptionsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserOptionsService {

    private final UserOptionsRepository userOptionsRepository;
    private final UserService userService;;

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

    public Optional<UserOptions> getUserOptionsByUserId(UUID userId) {
        return userOptionsRepository.findByUserId(userId);
    }

    public static UserOptionsDTO convertOptionsToDto(UserOptions uo) {
        return new UserOptionsDTO(
                uo.getId(),
                uo.isEmailSubscription(),
                uo.getUser().getId()
        );
    }

    public UserOptions convertDtoToOptions(UserOptionsDTO uoDto) {

        return new UserOptions(
                uoDto.id(),
                uoDto.emailSubscription(),
                userService.getUserById(uoDto.userId()).orElseGet(null)
        );
    }


}
