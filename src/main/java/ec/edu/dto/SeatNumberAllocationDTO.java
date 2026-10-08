package ec.edu.dto;

import java.util.List;

import ec.edu.model.BookingTicketNumber;
import ec.edu.model.SeatSetup;
import ec.edu.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatNumberAllocationDTO {
       
	
	  private int seatNumberAllocationId;
	  
       private String seatNumber;
       
//       private String seatStatus;
       
    
       private SeatSetup seatsetup; 
       private List<BookingTicketNumber> bookingTicketNumber;
}
