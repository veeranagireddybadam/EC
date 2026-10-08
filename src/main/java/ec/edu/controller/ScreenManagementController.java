package ec.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.dto.ScreenManagementDTO;
import ec.edu.dto.ScreenManagementWithBookingDTO;
import ec.edu.service.ScreenManagementService;

@CrossOrigin("*")
@RestController
public class ScreenManagementController {

    @Autowired
    private ScreenManagementService screenManagementService;

    @PostMapping("/saveAll")
    public ResponseEntity<String> saveAll(@RequestBody ScreenManagementDTO dto) {
        screenManagementService.saveAll(dto);
        return ResponseEntity.ok("Saved successfully");
    }
    
    
    @GetMapping("/getScreenManagement/{screenId}")
    public ScreenManagementDTO getScreenManagementById(@PathVariable int screenId) {
        return screenManagementService.getScreenManagementById(screenId);
    }
    
    
    
    
    
    
    
    
    

    @PutMapping("/updateAll/{screenId}")
    public ResponseEntity<String> updateAll(@RequestBody ScreenManagementDTO dto , @PathVariable int screenId) {
        screenManagementService.updateAll(dto, screenId);
        return ResponseEntity.ok("Updated successfully");
    }
    
    @GetMapping("/getScreenManagementWithBooking")
    public ResponseEntity<ScreenManagementWithBookingDTO> getScreenManagementWithBooking(
            @RequestParam int screenId,
            @RequestParam int movieSetupId) {
        ScreenManagementWithBookingDTO dto = screenManagementService.getScreenManagementWithBookings(screenId, movieSetupId);
        return ResponseEntity.ok(dto);
    }
	
	
}
