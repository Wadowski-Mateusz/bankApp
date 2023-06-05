package bankApp.services;

import bankApp.DTOs.UserDTO;
import bankApp.DTOs.UserVerificationDTO;
import bankApp.entities.Address;
import bankApp.entities.User;
import bankApp.entities.UserDetails;
import bankApp.exceptions.UserNotFoundException;
import bankApp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private final AddressService addressService;


    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUserById(UUID id) {
        userRepository.deleteById(id);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public UserDTO convertUserToDTO(User user) throws UserNotFoundException {
        return new UserDTO(
                user.getId(),
                userDetailsService.getFullNameByUserId(user.getId()),
                user.getRole().getRole()
        );
    }

    public UserVerificationDTO convertUserToVerificationDTO(User user) throws UserNotFoundException {
        UserDetails userDetails = userDetailsService.getByUserId(user.getId())
                .orElseThrow(() -> new UserNotFoundException("UserDetails not found"));
        Address address = addressService.getAddressByUserDetailsId(userDetails.getId())
                .orElseThrow(() -> new UserNotFoundException("Address not found"));
        return new UserVerificationDTO(
                user.getId().toString(),
                userDetails.getFullName(),
                userDetails.getEmail(),
                userDetails.getBirthday().toString(),
                AddressService.convertAddressToDTO(address),
                userDetails.getIdNumber()
        );
    }

    public List<User> getAllUnverified() {
        return userRepository.findAllByIsVerified(false);
    }

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
