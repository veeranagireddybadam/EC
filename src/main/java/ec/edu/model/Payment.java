package ec.edu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity

public class Payment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int paymentId;
	
	@Column(name="transaction_id")
	private String transactionId;
	
	@Column(name="payment_status")
	private String paymentStatus;
	
	@Column(name="cardnumber")
	private String cardnumber;
	
	@Column(name="expiredate")
	private String expiredate;
	
	@OneToOne
	@JoinColumn(name = "bookingTicketId")
	private BookingTicket bookingticket;

	
}