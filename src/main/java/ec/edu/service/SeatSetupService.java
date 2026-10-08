package ec.edu.service;

import java.util.List;

import ec.edu.dto.SeatSetupDTO;
import ec.edu.model.SeatSetup;

public interface SeatSetupService {

	SeatSetup createSeatRow(SeatSetupDTO seatsetupDTO);

	List<SeatSetupDTO> fetchallSeatRow();

	SeatSetupDTO getoneSeatssetup(int seatSetupId);

	SeatSetup updateSeatSet(SeatSetupDTO seatsetupDTO, int seatSetupId);

	void deleteSeatSetup(int seatSetupId);

	void createSeatSetupWithSeatNumbers(SeatSetup seatSetup);

	void save(SeatSetupDTO setupDTO);

}
