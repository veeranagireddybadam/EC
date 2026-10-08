package ec.edu.service;

import ec.edu.dto.ScreenManagementDTO;
import ec.edu.dto.ScreenManagementWithBookingDTO;

public interface ScreenManagementService {

	void saveAll(ScreenManagementDTO dto);

	void updateAll(ScreenManagementDTO dto , int screenId);

	ScreenManagementDTO getScreenManagementById(int screenId);

	ScreenManagementWithBookingDTO getScreenManagementWithBookings(int screenId, int movieSetupId);

}
