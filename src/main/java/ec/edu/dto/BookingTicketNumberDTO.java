package ec.edu.dto;

import java.util.Date;
import java.util.List;

import ec.edu.model.BookingTicket;
import ec.edu.model.Role;
import ec.edu.model.Screens;
import ec.edu.model.SeatNumberAllocation;
import ec.edu.model.SeatSetup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
 public class BookingTicketNumberDTO {

	private  int bookingticketnumberId;
	
	private SeatNumberAllocation seatnumberallocation;
	//private SeatSetup seatsetup;
	private BookingTicket bookingticket;
	
}