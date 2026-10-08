package ec.edu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.dto.ScreenSeatClassDTO;
import ec.edu.dto.ScreensDTO;
import ec.edu.model.Role;
import ec.edu.model.ScreenSeatClass;
import ec.edu.model.Screens;
import ec.edu.model.User;
import ec.edu.repository.ScreenSeatClassRepository;
import ec.edu.repository.ScreensRepository;

@Service
public class ScreenSeatClassServiceImp implements ScreenSeatClassService {
	
	@Autowired
	private ScreenSeatClassRepository screenseatclassRepository;
	@Autowired
	private ScreensRepository screensRepository;

	
	
	@Override
	public ScreenSeatClass createScreenSeatClass(ScreenSeatClassDTO screenseatclassDTO) {
	
		ScreenSeatClass screenseatclass =   new ScreenSeatClass();
		
		screenseatclass.setScreen_seat_type_id(screenseatclassDTO.getScreen_seat_type_id());
		screenseatclass.setSeat_class(screenseatclassDTO.getSeat_class());
		screenseatclass.setTotal_seat_count(screenseatclassDTO.getTotal_seat_count());
		screenseatclass.setPrice_per_seat(screenseatclassDTO.getPrice_per_seat());
//		screenseatclass.setScreens(screenseatclassDTO.getScreens());
	
		Screens screens =screensRepository.findById(screenseatclassDTO.getScreens().getScreenId()).orElse(null);
		screenseatclass.setScreens(screens);
		
		
//		Role role=rolerepository.findById(userdto.getRole().getRoleId()).orElse(null);  
////	  	user.setRole(role);
	
		screenseatclassRepository.save(screenseatclass);
		
		return screenseatclass;
	}



	@Override
	public List<ScreenSeatClassDTO> fetchallScreenSeatClass() {
		
		
		ArrayList<ScreenSeatClassDTO> screenseatclassDTOs = new ArrayList<>();
		List<ScreenSeatClass> screenseatclass = screenseatclassRepository.findAll();
		
		for(ScreenSeatClass screenseat:screenseatclass) {
			
			ScreenSeatClassDTO  screenSeatClassDTO = new ScreenSeatClassDTO();
			
			screenSeatClassDTO.setScreen_seat_type_id(screenseat.getScreen_seat_type_id());
			screenSeatClassDTO.setPrice_per_seat(screenseat.getPrice_per_seat());	
			screenSeatClassDTO.setTotal_seat_count(screenseat.getTotal_seat_count());
			screenSeatClassDTO.setSeat_class(screenseat.getSeat_class());
			
//			   if (screenseat.getScreens() != null) {
//		            Screens screens = screensRepository
//		                .findById(screenseat.getScreens().getScreenId())
//		                .orElse(null);
//		            screenSeatClassDTO.setScreens(screens);
//		        } else {
//		            screenSeatClassDTO.setScreens(null);
//		        }
//			screensdto.setUser(screen.getUser());
			
//		
			
			
	        screenseatclassDTOs.add(screenSeatClassDTO);
			}
		return screenseatclassDTOs;
	
		
	}



	@Override
	public ScreenSeatClassDTO getoneScreenSeatClass(int screen_seat_type_id) {
		ScreenSeatClass screenseatclass = screenseatclassRepository.findById(screen_seat_type_id).orElse(null);
		
		ScreenSeatClassDTO screenseatclassdto = new ScreenSeatClassDTO();
		
		screenseatclassdto.setPrice_per_seat(screenseatclass.getPrice_per_seat());
		screenseatclassdto.setScreen_seat_type_id(screenseatclass.getScreen_seat_type_id());
		screenseatclassdto.setSeat_class(screenseatclass.getSeat_class());
		screenseatclassdto.setTotal_seat_count(screenseatclass.getTotal_seat_count());
		
		Screens screens = screensRepository.findById(screenseatclass.getScreens().getScreenId()).orElse(null);
		screenseatclassdto.setScreens(screens);
		
		
		
		return screenseatclassdto;
	}



	@Override
	public ScreenSeatClass updateScreenSeatClass(ScreenSeatClassDTO screenseatclassDTO, int screen_seat_type_id) {
	  
	    ScreenSeatClass screenseatclass = screenseatclassRepository.findById(screen_seat_type_id)
	            .orElseThrow(() -> new RuntimeException("Seat class not found with ID: " + screen_seat_type_id));
	    
	    screenseatclass.setPrice_per_seat(screenseatclassDTO.getPrice_per_seat());
	    screenseatclass.setScreen_seat_type_id(screenseatclassDTO.getScreen_seat_type_id());
	    screenseatclass.setSeat_class(screenseatclassDTO.getSeat_class());
	    screenseatclass.setTotal_seat_count(screenseatclassDTO.getTotal_seat_count());

	    if (screenseatclassDTO.getScreens() != null && screenseatclassDTO.getScreens().getScreenId() != 0) {
	        Screens screens = screensRepository.findById(screenseatclassDTO.getScreens().getScreenId())
	                .orElseThrow(() -> new RuntimeException("Screen not found with ID: " + screenseatclassDTO.getScreens().getScreenId()));
	        screenseatclass.setScreens(screens);
	    }

	    // ✅ Save the updated entity
	    return screenseatclassRepository.save(screenseatclass);
	}



	@Override
	public void deleteScreenSeatClass(int screen_seat_type_id) {
		  ScreenSeatClass  screenseatclass = screenseatclassRepository.findById(screen_seat_type_id)
			        .orElseThrow(() -> new RuntimeException("screens not found with ID: " + screen_seat_type_id));

			  
		  screenseatclassRepository.delete(screenseatclass);
		
	}



	@Override
	public void save(ScreensDTO seatClassDTO) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void updateScreenSeatPrices(ScreensDTO screensDTO, int screenId) {
		 List<ScreenSeatClass> existingSeatClasses = screenseatclassRepository.findByScreensScreenId(screenId);

	   
	        Map<Integer, Double> updatedPrices = screensDTO.getScreenseatclass().stream()
	            .collect(Collectors.toMap(
	                ScreenSeatClassDTO::getScreen_seat_type_id,
	                ScreenSeatClassDTO::getPrice_per_seat
	            ));

	       
	        for (ScreenSeatClass seatClass : existingSeatClasses) {
	            if (updatedPrices.containsKey(seatClass.getScreen_seat_type_id())) {
	                seatClass.setPrice_per_seat(updatedPrices.get(seatClass.getScreen_seat_type_id()));
	            }
	        }

	      
	        screenseatclassRepository.saveAllAndFlush(existingSeatClasses);
		
	}



	@Override
	public void updatePricesByScreenId(int screenId, List<ScreenSeatClass> updates) {
		// TODO Auto-generated method stub
		List<ScreenSeatClass> existing = screenseatclassRepository.findByScreensScreenId(screenId);

        Map<Integer, Double> updateMap = updates.stream()
                .collect(Collectors.toMap(ScreenSeatClass::getScreen_seat_type_id, ScreenSeatClass::getPrice_per_seat));

        for (ScreenSeatClass seatClass : existing) {
            if (updateMap.containsKey(seatClass.getScreen_seat_type_id())) {
                seatClass.setPrice_per_seat(updateMap.get(seatClass.getScreen_seat_type_id()));
            }
        }

        screenseatclassRepository.saveAllAndFlush(existing);
	}





}
