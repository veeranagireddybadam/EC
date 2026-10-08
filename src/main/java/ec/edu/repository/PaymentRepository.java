package ec.edu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.edu.model.BookingTicket;
import ec.edu.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer>{

	  Optional<Payment> findByBookingticket_BookingTicketId(int bookingTicketId);
	  
	  Optional<Payment> findByBookingticket(BookingTicket bookingTicket);

	
	
}