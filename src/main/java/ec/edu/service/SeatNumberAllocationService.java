package ec.edu.service;

import java.util.List;

import ec.edu.dto.SeatNumberAllocationDTO;
import ec.edu.model.ScreenSeatClass;
import ec.edu.model.SeatNumberAllocation;
import ec.edu.model.SeatSetup;

public interface SeatNumberAllocationService {

	SeatNumberAllocation createSeatNumber(SeatNumberAllocationDTO seatnumberallocationDTO);

	List<SeatNumberAllocationDTO> fetchAllSeatNumber();

	List<SeatNumberAllocation> generateSeatsFromSetup(SeatSetup setup);

	List<SeatNumberAllocation> generateSeatNumbers(SeatSetup seatSetup);

	void generateSeatsForScreenSeatClass(ScreenSeatClass screenSeatClass, int seatPerRow);

	void save(SeatNumberAllocationDTO seatNumberDTO);

}
