package ec.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.dto.BookingTicketNumberDTO;
import ec.edu.model.BookingTicket;
import ec.edu.model.BookingTicketNumber;
import ec.edu.repository.BookingTicketNumberRepository;
import ec.edu.repository.BookingTicketRepository;

@Service
public class BookingTicketNumberServiceImp implements BookingTicketNumberService{
//
//	@Override
//	public BookingTicketNumber saveBookingTicketNumber(BookingTicketNumberDTO dto) {
//		// TODO Auto-generated method stub
//		return null;
//		
//		
//	}
	
	 	@Autowired
	    private BookingTicketNumberRepository bookingTicketNumberRepository;
	 	
	 	@Autowired
	    private BookingTicketRepository bookingTicketRepository;


	 	public BookingTicketNumber saveBookingTicketNumber(BookingTicketNumberDTO dto) {
	 	    BookingTicketNumber bookingTicketNumber = new BookingTicketNumber();

	 	    bookingTicketNumber.setSeatNumberAllocation(dto.getSeatnumberallocation());

	 	    // Fetch full BookingTicket entity by ID
	 	    BookingTicket ticket = bookingTicketRepository.findById(dto.getBookingticket().getBookingTicketId())
	 	                         .orElseThrow(() -> new RuntimeException("Booking ticket not found with id: " + dto.getBookingticket().getBookingTicketId()));
	 	    bookingTicketNumber.setBookingticket(ticket);

	 	    return bookingTicketNumberRepository.save(bookingTicketNumber);
	 	}

	
	

}
