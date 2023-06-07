package bankApp.controllers;

import bankApp.DTOs.AnnouncementDTO;
import bankApp.DTOs.AnnouncementDeleteDTO;
import bankApp.entities.Announcement;
import bankApp.services.AnnouncementService;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;


@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/announcement")
public class AnnouncementController {
    private final AnnouncementService announcementService;


    @GetMapping("/all")
    ResponseEntity<List<AnnouncementDTO>> getAllAnnouncements(){
        List<AnnouncementDTO> announcementDTOS = announcementService.getAllAnnouncements()
                .stream()
                .map(AnnouncementService::convertAnnouncementToDTO)
                .toList();

        return ResponseEntity.ok(announcementDTOS);
    }

    @GetMapping("/available")
    ResponseEntity<List<AnnouncementDTO>> getAllAvailableAnnouncements(){
        List<AnnouncementDTO> announcementDTOS = announcementService.getAllAnnouncements()
                .stream()
                .filter(announcement -> announcement.getDateTo().isAfter(LocalDate.now()))
                .filter(announcement -> announcement.getDeletedBy() == null)
                .map(AnnouncementService::convertAnnouncementToDTO)
                .toList();
        return ResponseEntity.ok(announcementDTOS);
    }


    @GetMapping("/current/all")
    ResponseEntity<List<AnnouncementDTO>> getAllCurrentAnnouncements(){
        List<AnnouncementDTO> announcementDTOS = announcementService.getAllAnnouncements()
                .stream()
                .filter(announcement -> announcement.getDateTo().isAfter(LocalDate.now()))
                .filter(announcement -> announcement.getDateFrom().isBefore(LocalDate.now().plusDays(1)))
                .filter(announcement -> announcement.getDeletedBy() == null)
                .map(AnnouncementService::convertAnnouncementToDTO)
                .toList();

        return ResponseEntity.ok(announcementDTOS);
    }

    @GetMapping("/current/random")
    ResponseEntity<AnnouncementDTO> getRandomCurrentAnnouncement(){
        List<AnnouncementDTO> announcementDTOS = announcementService.getAllAnnouncements()
                .stream()
                .filter(announcement -> announcement.getDateFrom().isBefore(LocalDate.now()))
                .filter(announcement -> announcement.getDateTo().isAfter(LocalDate.now()))
                .filter(announcement -> announcement.getDeletedBy() == null)
                .map(AnnouncementService::convertAnnouncementToDTO)
                .toList();
        AnnouncementDTO announcementDTO = new AnnouncementDTO(null, "", null, null, null);
        if(!announcementDTOS.isEmpty()){
            int i = (new Random()).nextInt(announcementDTOS.size());
            announcementDTO = announcementDTOS.get(i);
        }
        return ResponseEntity.ok(announcementDTO);
    }

    @PostMapping("/add")
    ResponseEntity<AnnouncementDTO> addAnnouncement(@RequestBody AnnouncementDTO announcementDTO) {
        try {
            Announcement announcement = announcementService.convertDtoToAnnouncement(announcementDTO);
            announcement.setCreated(LocalDateTime.now());
            Announcement createdAnnouncement = announcementService.createAnnouncement(announcement);
            return ResponseEntity.ok(AnnouncementService.convertAnnouncementToDTO(createdAnnouncement));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/delete")
    ResponseEntity<AnnouncementDTO> deleteAnnouncement(@RequestBody AnnouncementDeleteDTO announcementDeleteDTO) {
        try {
            Announcement announcementFromDB = announcementService.getAnnouncementById(announcementDeleteDTO.announcementId());
            announcementService.setDeletingUser(announcementFromDB, announcementDeleteDTO.deletingUserId());
            Announcement updatedAnnouncement = announcementService.updateAnnouncement(announcementFromDB);
            return ResponseEntity.ok(AnnouncementService.convertAnnouncementToDTO(updatedAnnouncement));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
