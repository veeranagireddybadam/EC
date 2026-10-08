package ec.edu.service;

import ec.edu.dto.BookingTicketNumberDTO;
import ec.edu.model.BookingTicketNumber;

public interface BookingTicketNumberService {

	BookingTicketNumber saveBookingTicketNumber(BookingTicketNumberDTO dto);

}
