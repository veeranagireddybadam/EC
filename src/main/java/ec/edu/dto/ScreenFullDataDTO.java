package ec.edu.dto;

import java.util.List;

import ec.edu.model.SeatSetup;
import ec.edu.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScreenFullDataDTO {

	   private ScreensDTO screen;
	    private List<ScreenSeatClassDTO> seatClasses;
	    private List<SeatSetupDTO> seatSetups;
	    private List<SeatNumberAllocationDTO> seatNumberAllocations;
		public Iterable<ScreensDTO> getSeatClasses() {
			// TODO Auto-generated method stub
			return null;
		}
	
}
