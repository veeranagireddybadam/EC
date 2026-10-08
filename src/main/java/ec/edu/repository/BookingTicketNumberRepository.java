package ec.edu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ec.edu.model.BookingTicket;
import ec.edu.model.BookingTicketNumber;

@Repository
public interface BookingTicketNumberRepository extends JpaRepository<BookingTicketNumber, Integer>{
    @Query("SELECT b.seatNumberAllocation.seatNumberAllocationId FROM BookingTicketNumber b " +
            "WHERE b.bookingticket.movieSetup.movieSetupId = :movieSetupId")
     List<Integer> findBookedSeatAllocationIdsByMovieSetupId(int movieSetupId);

	List<BookingTicketNumber> findByBookingticket(BookingTicket booking);
}
