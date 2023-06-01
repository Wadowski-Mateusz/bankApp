package bankApp.controllers;


import bankApp.DTOs.UserOptionsDTO;
import bankApp.entities.UserOptions;
import bankApp.exceptions.UserNotFoundException;
import bankApp.services.UserOptionsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/user_options")
public class UserOptionsController {

    private final UserOptionsService userOptionsService;

    @GetMapping
    public ResponseEntity<UserOptionsDTO> getUserSettingsByUserId(@RequestParam UUID userId) {
        try {
            Optional<UserOptions> uo = userOptionsService.getUserOptionsByUserId(userId);
            if(uo.isEmpty())
                throw new UserNotFoundException("[UO] User with given id not found");
            return ResponseEntity.ok(UserOptionsService.convertOptionsToDto(uo.get()));
        } catch (UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<UserOptionsDTO> setUserSettings(@RequestBody UserOptionsDTO uoDTO) {
        try {
            UserOptions uo = userOptionsService.convertDtoToOptions(uoDTO);
            UserOptions uoUpdated = userOptionsService.updateUserOptions(uo);
            return ResponseEntity.ok(UserOptionsService.convertOptionsToDto(uoUpdated));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
