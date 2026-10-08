package ec.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.dto.BookingTicketNumberDTO;
import ec.edu.model.BookingTicketNumber;
import ec.edu.service.BookingTicketNumberService;

@RestController
@CrossOrigin("*")
public class BookingTicketNumberController {
	
    @Autowired
    private BookingTicketNumberService bookingTicketNumberService;
	
	@PostMapping("/addbookingticketnumber")
    public BookingTicketNumber addBookingTicketNumbers(@RequestBody BookingTicketNumberDTO dto) {
        return bookingTicketNumberService.saveBookingTicketNumber(dto);
    }

}
