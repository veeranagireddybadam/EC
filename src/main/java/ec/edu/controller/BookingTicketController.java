package ec.edu.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.dto.BookingHistoryDTO;
import ec.edu.dto.BookingRequestDTO;
import ec.edu.dto.BookingTicketDTO;
import ec.edu.model.BookingTicket;
import ec.edu.model.BookingTicketNumber;
import ec.edu.model.MovieSetup;
import ec.edu.model.SeatNumberAllocation;
import ec.edu.model.User;
import ec.edu.repository.BookingTicketRepository;
import ec.edu.service.BookingTicketService;
import ec.edu.service.MovieSetupService;
import ec.edu.service.SeatNumberAllocationService;
import ec.edu.service.UserService;

@RestController
@CrossOrigin("*")
public class BookingTicketController {
	
	@Autowired
	private BookingTicketService bookingticketService;

    @Autowired
    private UserService userService;

    @Autowired
    private MovieSetupService movieSetupService;

    @Autowired
    private SeatNumberAllocationService seatNumberAllocationService;
    
    @Autowired
    private BookingTicketRepository bookingTicketRepository;
    
 
    
	
	@PostMapping("/saveBookingTicket")
	public ResponseEntity<BookingTicket>createBookingTicket(@RequestBody BookingTicketDTO bookingticketDTO){
		BookingTicket bookingticket=bookingticketService.createBookingTicket(bookingticketDTO);
		return new ResponseEntity<>(bookingticket,HttpStatus.CREATED);
	}
	
	@GetMapping("/fetchallbookingticketdetails")
	public ResponseEntity<List<BookingTicketDTO>>fetchallbookingticketdetails(){
		List<BookingTicketDTO> bookingticketDTOs=bookingticketService.fetchallbookingticketdetails();
		return new ResponseEntity<>(bookingticketDTOs,HttpStatus.OK);
		
	}
	
	@GetMapping("/fetchonebookingticket/{bookingTicketId}")
	public ResponseEntity<BookingTicketDTO> fetchonebookingticket(@PathVariable int bookingTicketId) {
	    BookingTicketDTO bookingticketDTOs = bookingticketService.fetchonebookingticket(bookingTicketId);
	    return new ResponseEntity<>(bookingticketDTOs, HttpStatus.OK);
	}

	
	@PutMapping("/API/updatebookingticket/{bookingTicketId}")
	public ResponseEntity<BookingTicket> updateBookingTicket(
	        @RequestBody BookingTicketDTO bookingTicketDTO,
	        @PathVariable int bookingTicketId) {
	    
	    BookingTicket updatedBookingTicket = bookingticketService.updateBookingTicket(bookingTicketId, bookingTicketDTO);
	    return new ResponseEntity<>(updatedBookingTicket, HttpStatus.OK);
	}

	@DeleteMapping("/API/deletebookingticket/{bookingTicketId}")
	public ResponseEntity<String> deleteBookingTicket(@PathVariable int bookingTicketId) {
		bookingticketService.deleteBookingTicket(bookingTicketId);
	    return new ResponseEntity<>("Deleted booking ticket with Id " + bookingTicketId, HttpStatus.OK);
	}
	
	
	@PostMapping("/book")
	public ResponseEntity<String> bookSeats(@RequestBody BookingRequestDTO bookingRequest) {
	    try {
	        bookingticketService.bookSeats(bookingRequest);
	        return ResponseEntity.ok("Booking Successful");
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Booking Failed");
	    }
	}
	@PutMapping("/updateBookingStatus/{bookingTicketId}")
	public ResponseEntity<BookingTicket> updateBookingStatus(@PathVariable int bookingTicketId, @RequestBody Map<String, String> body) {
	    String newStatus = body.get("bookingStatus");
	    BookingTicket ticket = bookingTicketRepository.findById(bookingTicketId)
	                                .orElseThrow(() -> new RuntimeException("Booking not found"));
	    ticket.setBookingStatus(newStatus);
	    bookingTicketRepository.save(ticket);
	    return ResponseEntity.ok(ticket);
	}
	
	 @GetMapping("/fetchAllBookingHistory")
	    public ResponseEntity<List<BookingHistoryDTO>> fetchAllBookingHistories() {
	        return ResponseEntity.ok(bookingticketService.getAllBookingHistories());
	    }
	
	}
