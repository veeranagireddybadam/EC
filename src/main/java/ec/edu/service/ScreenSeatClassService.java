package ec.edu.service;

import java.util.List;

import ec.edu.dto.ScreenSeatClassDTO;
import ec.edu.dto.ScreensDTO;
import ec.edu.model.ScreenSeatClass;

public interface ScreenSeatClassService {

	ScreenSeatClass createScreenSeatClass(ScreenSeatClassDTO screenseatclassDTO);

	List<ScreenSeatClassDTO> fetchallScreenSeatClass();

	ScreenSeatClassDTO getoneScreenSeatClass(int screen_seat_type_id);

	ScreenSeatClass updateScreenSeatClass(ScreenSeatClassDTO screenseatclassDTO, int screen_seat_type_id);

	void deleteScreenSeatClass(int screen_seat_type_id);

	void save(ScreensDTO seatClassDTO);

	void updateScreenSeatPrices(ScreensDTO screensDTO, int screenId);

	void updatePricesByScreenId(int screenId, List<ScreenSeatClass> updates);

//	void updateScreenSeatPrices(ScreensDTO screensDTO, int screenId);

//	void updateScreenSeatPrices(ScreensDTO screensDTO, int screenId);

}
