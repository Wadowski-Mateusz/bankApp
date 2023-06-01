package bankApp.controllers;

import bankApp.DTOs.AnnouncementDTO;
import bankApp.services.AnnouncementService;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/announcement")
public class AnnouncementController {
    private final AnnouncementService announcementService;

    @GetMapping("/current/random")
    ResponseEntity<AnnouncementDTO> getRandomCurrentAnnouncement(){
        List<AnnouncementDTO> announcementDTOS = announcementService.getAllAnnouncements()
                .stream()
                .filter(announcement -> announcement.getDateTo().isAfter(LocalDateTime.now()))
                .filter(announcement -> announcement.getDateFrom().isBefore(LocalDateTime.now()))
                .map(AnnouncementService::convertAnnouncementToDTO)
                .toList();
        AnnouncementDTO announcementDTO = new AnnouncementDTO(null, "", null, null);
        if(!announcementDTOS.isEmpty()){
            int i = (new Random()).nextInt(announcementDTOS.size());
            announcementDTO = announcementDTOS.get(i);
        }

        return ResponseEntity.ok(announcementDTO);
    }

    @PostMapping("/add")
    ResponseEntity<AnnouncementDTO> addAnnouncement(AnnouncementDTO announcementDTO) {
        
    }



}
