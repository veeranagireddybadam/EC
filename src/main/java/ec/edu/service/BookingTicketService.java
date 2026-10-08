package ec.edu.service;

import java.util.List;

import ec.edu.dto.BookingHistoryDTO;
import ec.edu.dto.BookingRequestDTO;
import ec.edu.dto.BookingTicketDTO;
import ec.edu.model.BookingTicket;
import ec.edu.model.BookingTicketNumber;

public interface BookingTicketService {

	BookingTicket createBookingTicket(BookingTicketDTO bookingticketDTO);

	List<BookingTicketDTO> fetchallbookingticketdetails();

	BookingTicketDTO fetchonebookingticket(int bookingTicketId);

	BookingTicket updateBookingTicket(int bookingTicketId, BookingTicketDTO bookingTicketDTO);

	void deleteBookingTicket(int bookingTicketId);

	static BookingTicket save(BookingTicket bookingTicket) {
		// TODO Auto-generated method stub
		return null;
	}

	void bookSeats(BookingRequestDTO bookingRequest);

	List<BookingHistoryDTO> getAllBookingHistories();
//	Object getAllBookingHistories();

//	void saveBookingTicketNumber(BookingTicketNumber btn);


}