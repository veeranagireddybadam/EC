package ec.edu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ec.edu.model.BookingTicket;


@Repository
public interface BookingTicketRepository extends JpaRepository<BookingTicket,Integer> {
	@Query(value = "SELECT " +
            "bt.booking_ticket_id AS bookingTicketId, " +
            "p.transaction_id AS transactionId, " +
            "u.user_name AS userName, " +
            "m.movie_title AS movieName, " +
            "s.screen_name AS screenName, " +
            "ms.show_date AS showDate, " +
            "ms.showtime AS showTime, " +
            "GROUP_CONCAT(sna.seat_number ORDER BY sna.seat_number ASC SEPARATOR ', ') AS seats, " +
            "bt.total_price AS amount " +
            "FROM booking_ticket bt " +
            "JOIN payment p ON p.booking_ticket_id = bt.booking_ticket_id " +
            "JOIN user u ON bt.user_id = u.user_id " +
            "JOIN movie_setup ms ON bt.movie_setup_id = ms.movie_setup_id " +
            "JOIN movie m ON ms.movie_id = m.movie_id " +
            "JOIN screens s ON ms.screen_id = s.screen_id " +
            "JOIN booking_ticket_number btn ON btn.booking_ticket_id = bt.booking_ticket_id " +
            "JOIN seat_number_allocation sna ON sna.seat_number_allocation_id = btn.seat_number_allocation_id " +
            "GROUP BY bt.booking_ticket_id, p.transaction_id, u.user_name, m.movie_title, s.screen_name, ms.show_date, ms.showtime, bt.total_price",
            nativeQuery = true)
    List<Object[]> fetchBookingHistory();

}