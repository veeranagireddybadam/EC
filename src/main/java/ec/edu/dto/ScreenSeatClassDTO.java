package ec.edu.dto;

import java.util.List;

import ec.edu.model.Screens;
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
public class ScreenSeatClassDTO {
    private int screen_seat_type_id;
    private String seat_class;
    private int total_seat_count;
    private double price_per_seat;
    
	private List<SeatSetup> seatsetup;
	
	private List<SeatSetupDTO> seatsetups;
	
//	private SeatSetup seatsetups;
	
	private SeatSetupDTO seatsetupdto; 
	
	private Screens screens;
    
}


//@Data
//public class ScreenSeatClassDTO {
//	
//   private int screen_seat_type_id;
//	
//	private String seat_class;
//	
//	private int total_seat_count;
//	
//	private double price_per_seat;
//	
//	


//	private List<SeatSetup> seatsetup;
//
//}
