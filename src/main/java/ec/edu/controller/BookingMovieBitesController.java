package ec.edu.controller;

import ec.edu.dto.BookingMovieBiteDTO;
import ec.edu.dto.BookingMovieBitesRequest;
import ec.edu.model.BookingMovieBites;
import ec.edu.service.BookingMovieBitesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class BookingMovieBitesController {

    @Autowired
    private BookingMovieBitesService bookingMovieBitesService;

    @PostMapping("/addmoviebiteswithbooking")
    public ResponseEntity<?> addMovieBitesWithBookingId(@RequestBody BookingMovieBitesRequest request) {
        try {
            bookingMovieBitesService.saveBookingMovieBites(request.getBookingTicketId(), request.getSnacks());
            return ResponseEntity.ok("Booking movie bites saved successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to save booking movie bites: " + e.getMessage());
        }
    }
}
