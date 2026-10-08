package ec.edu.dto;

import java.util.List;

import ec.edu.model.ScreenSeatClass;
import ec.edu.model.Screens;
import ec.edu.model.SeatNumberAllocation;
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
public class SeatSetupDTO {
	  private int seatSetupId;
		
	     private String rowLabel;
	     
	     private int seat_PerRow;
	     
	     private ScreenSeatClass screenseatclass;
	     
	     
	     private List <SeatNumberAllocationDTO> seatnumberallocation;
	     
	 
	     private Screens screens;
	     
}
