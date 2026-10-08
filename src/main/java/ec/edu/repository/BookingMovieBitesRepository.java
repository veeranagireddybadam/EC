package ec.edu.repository;

import ec.edu.model.BookingMovieBites;
import ec.edu.model.BookingTicket;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingMovieBitesRepository extends JpaRepository<BookingMovieBites, Integer> {
	List<BookingMovieBites> findByBookingTicketBookingTicketId(int bookingTicketId);
	 List<BookingMovieBites> findByBookingTicket(BookingTicket bookingTicket);
}
