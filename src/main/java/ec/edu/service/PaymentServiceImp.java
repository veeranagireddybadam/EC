package ec.edu.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.dto.PaymentDTO;
import ec.edu.model.BookingTicket;
import ec.edu.model.Payment;
import ec.edu.repository.BookingTicketRepository;
import ec.edu.repository.PaymentRepository;

@Service
public class PaymentServiceImp implements PaymentService {

	@Autowired
	private BookingTicketRepository  bookingTicketRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	
	
	
	@Override
	public Payment createpayment(PaymentDTO paymentDTO) {
		
		Payment payment = new Payment();
		payment.setCardnumber(paymentDTO.getCardnumber());
		payment.setExpiredate(paymentDTO.getExpiredate());
		//payment.setPaymentStatus(paymentDTO.getPaymentStatus());
		
		String txnId = "TXN" + Instant.now().toEpochMilli(); // Or use UUID/random string
	    payment.setTransactionId(txnId);
		
	    
		
		
		
		return null;
	}

	
	@Override
	public List<PaymentDTO> fetchallpayments() {
	
		return null;
	}

	
	@Override
	public PaymentDTO getOnePayment(int paymentId) {
	
		return null;
	}


	@Override
	public String savePayment (PaymentDTO dto) {
	
		Payment payment  = new Payment();
		
		String txnId = "TXN" + Instant.now().toEpochMilli(); // Or use UUID/random string
		    payment.setTransactionId(txnId);

		    payment.setPaymentStatus(dto.getPaymentStatus());
		    payment.setCardnumber(dto.getCardnumber());
		    payment.setExpiredate(dto.getExpiredate());

		    BookingTicket booking = bookingTicketRepository.findById(dto.getBookingticket().getBookingTicketId())
		            .orElseThrow(() -> new RuntimeException("Booking ID not found"));

		    payment.setBookingticket(booking);
		    paymentRepository.save(payment);
		
		    return txnId;
		    
	}


}
