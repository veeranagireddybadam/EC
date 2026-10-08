package ec.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.dto.BookingTicketDTO;
import ec.edu.model.BookingTicket;
import ec.edu.service.EmailService;

@RestController
@CrossOrigin
public class BookingController {

    private  EmailService bookingEmailService;
    
//    @Autowired
//    private BookingService bookingService;

    public BookingController(EmailService bookingEmailService) {
        this.bookingEmailService = bookingEmailService;
    }

    @PostMapping("/paymentSuccess/{bookingTicketId}")
    public ResponseEntity<String> paymentSuccess(@PathVariable int bookingTicketId) {
        // Trigger email after payment success
        bookingEmailService.sendBookingConfirmationEmail(bookingTicketId);
        return ResponseEntity.ok("Booking confirmation email sent successfully.");
    }
    
}
