package ec.edu.dto;

import ec.edu.model.BookingTicket;
import lombok.Data;

@Data
public class PaymentDTO {

	
	private int paymentId;
	private String transactionId;
	private String paymentStatus;
	private String cardnumber;
	private String expiredate;
	
	private BookingTicket bookingticket;

}