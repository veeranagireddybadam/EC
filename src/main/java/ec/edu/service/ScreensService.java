package ec.edu.service;

import java.util.List;

import ec.edu.dto.ScreenFullDataDTO;
import ec.edu.dto.ScreensDTO;
import ec.edu.model.Screens;

public interface ScreensService {

	Screens createScreen(ScreensDTO screensdto);

	List<ScreensDTO> fetchallScreens();

	ScreensDTO getonescreen(int screenId);

	Screens updateScreen(ScreensDTO screensDTO, int screenId);

	void deleteScreen(int screenId);

	ScreensDTO getbyname(String screenName);

	ScreensDTO getscreendetails(int screenId);

	Screens updateScreenDetails(ScreensDTO screensdto, int screenId);

	void saveScreenData(ScreenFullDataDTO screenFullDataDTO);

	void updateScreenSeatPrices(ScreensDTO screensDTO, int screenId);

	//Screens createAllScreen(ScreensDTO screensdto);

	Screens createFullScreen(ScreensDTO screensdto);



//	Screens save(ScreensDTO screen);

}
