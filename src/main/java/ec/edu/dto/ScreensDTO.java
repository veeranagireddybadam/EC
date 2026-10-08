package ec.edu.dto;

import java.util.List;

import ec.edu.model.ScreenSeatClass;
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
public class ScreensDTO {
    
	private int screenId;
	

    private String screenName;
    
 
    private int seatingCapacity;
    
    private User user;
    
    //private List <ScreenSeatClass> screenseatclass;
    
    private List<ScreenSeatClassDTO> screenseatclass;
    
    
    private List<SeatSetup> seatsetup;

	
	
	
}
