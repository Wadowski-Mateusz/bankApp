package bankApp.services;

import bankApp.DTOs.UserDetailsDTO;
import bankApp.entities.User;
import bankApp.entities.UserDetails;
import bankApp.exceptions.UserNotFoundException;
import bankApp.repositories.UserDetailsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;

    public UserDetails createUserDetails(UserDetails userDetails) {
        return userDetailsRepository.save(userDetails);
    }

    public Optional<UserDetails> getUserDetailsById(UUID id) {
        return userDetailsRepository.findById(id);
    }

    public List<UserDetails> getAllUserDetails() {
        return userDetailsRepository.findAll();
    }

    public void deleteUserDetailsById(UUID id) {
        userDetailsRepository.deleteById(id);
    }

    public UserDetails updateUserDetails(UserDetails userDetails) {
        return userDetailsRepository.save(userDetails);
    }

    public Optional<UserDetails> getByUserId(UUID userId) {
        Optional<UserDetails> userDetails = userDetailsRepository.findByUserId(userId);
        return userDetails;
    }

    public User getUserByUserId(UUID userId) {
        Optional<UserDetails> userDetails = userDetailsRepository.findByUserId(userId);
        return userDetails.map(UserDetails::getUser).orElse(null);
    }

    public String getFullNameByUserId(UUID userId) throws UserNotFoundException {
        Optional<UserDetails> userDetails = userDetailsRepository.findByUserId(userId);
        if(userDetails.isEmpty())
            throw new UserNotFoundException("User not found");
        return userDetails.get().getFullName();
    }

    public String getIdURIByUserId(UUID userId) throws UserNotFoundException {
        Optional<UserDetails> userDetails = userDetailsRepository.findByUserId(userId);
        if(userDetails.isEmpty())
            throw new UserNotFoundException("User not found");
        return userDetails.get().getIdUri();
    }

    public UserDetailsDTO convertUserDetailsToDTO(UserDetails userDetails) {
        return new UserDetailsDTO(
                userDetails.getFullName(),
                userDetails.getEmail(),
                userDetails.getBirthday()
        );
    }


}
