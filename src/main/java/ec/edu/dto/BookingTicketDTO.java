package ec.edu.dto;

import java.util.Date;
import java.util.List;

import ec.edu.model.Role;
import ec.edu.model.Screens;
import lombok.AllArgsConstructor;
import lombok.Builder;
//import ec.edu.model.MovieSetup;
//import ec.edu.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingTicketDTO {

	private int bookingTicketId;
	private double totalPrice;
	private String bookingStatus;
	private int numberofTickets;
	private Date bookingDate;
	
	
	private int userId;
	
	private int movieSetupId;
	
	 private List<BookingMovieBiteDTO> snacks;
	
}