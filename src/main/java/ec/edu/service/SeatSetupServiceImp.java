package ec.edu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.dto.ScreenSeatClassDTO;
import ec.edu.dto.SeatSetupDTO;
import ec.edu.model.ScreenSeatClass;
import ec.edu.model.Screens;
import ec.edu.model.SeatNumberAllocation;
import ec.edu.model.SeatSetup;
import ec.edu.repository.ScreenSeatClassRepository;
import ec.edu.repository.ScreensRepository;
import ec.edu.repository.SeatNumberAllocationRepository;
import ec.edu.repository.SeatSetupRepository;

@Service
public class SeatSetupServiceImp implements SeatSetupService {
    
	@Autowired
	private SeatSetupRepository seatsetuprepository;
	
	@Autowired
	private ScreenSeatClassRepository screenseatclassRepository;
	
	@Autowired
	private SeatNumberAllocationRepository  seatNumberAllocationRepository;
	
	@Autowired
	private ScreensRepository screensrepository;
	
	@Override
	public SeatSetup createSeatRow(SeatSetupDTO seatsetupDTO) {
		SeatSetup seatsetup = new SeatSetup();
		
		seatsetup.setRowLabel(seatsetupDTO.getRowLabel());
		seatsetup.setSeat_PerRow(seatsetupDTO.getSeat_PerRow());
		
		ScreenSeatClass screenseatclass = screenseatclassRepository.findById(seatsetupDTO.getScreenseatclass().getScreen_seat_type_id()).orElse(null);
		seatsetup.setScreenseatclass(screenseatclass);
		
		seatsetuprepository.save(seatsetup);
		
		return seatsetup;
	}

	@Override
	public List<SeatSetupDTO> fetchallSeatRow() {
		
		ArrayList<SeatSetupDTO> seatsetupDTOs = new ArrayList<>();
		List<SeatSetup> seatsetup = seatsetuprepository.findAll();
		
		for(SeatSetup seatsetuprow: seatsetup) {
			
			SeatSetupDTO  seatsetupDTO = new SeatSetupDTO();
			
			seatsetupDTO.setRowLabel(seatsetuprow.getRowLabel());
			seatsetupDTO.setSeatSetupId(seatsetuprow.getSeatSetupId());
			seatsetupDTO.setSeat_PerRow(seatsetuprow.getSeat_PerRow());
			
			ScreenSeatClass screenseatclass = screenseatclassRepository.findById(seatsetuprow.getScreenseatclass().getScreen_seat_type_id()).orElse(null);
			seatsetupDTO.setScreenseatclass(screenseatclass);
			
			Screens screens = screensrepository.findById(seatsetuprow.getScreens().getScreenId()).orElse(null);
			seatsetupDTO.setScreens(screens);
			
			
			
		seatsetupDTOs.add(seatsetupDTO);
			}
		return seatsetupDTOs;
}

	@Override
	public SeatSetupDTO getoneSeatssetup(int seatSetupId) {
         
	SeatSetup seatsetup = seatsetuprepository.findById(seatSetupId).orElse(null);
		
		SeatSetupDTO seatclassdto = new SeatSetupDTO();
		
		seatclassdto.setRowLabel(seatsetup.getRowLabel());
		seatclassdto.setSeat_PerRow(seatsetup.getSeat_PerRow());
		seatclassdto.setSeatSetupId(seatsetup.getSeatSetupId());
		ScreenSeatClass screenseatclass = screenseatclassRepository.findById(seatsetup.getScreenseatclass().getScreen_seat_type_id()).orElse(null);
		seatclassdto.setScreenseatclass(screenseatclass);
		
		Screens screens = screensrepository.findById(seatsetup.getScreens().getScreenId()).orElse(null);
		seatclassdto.setScreens(screens);
		
//		screenseatclassdto.setScreen_seat_type_id(screenseatclass.getScreen_seat_type_id());
//		screenseatclassdto.setSeat_class(screenseatclass.getSeat_class());
//		screenseatclassdto.setTotal_seat_count(screenseatclass.getTotal_seat_count());
//		
//		Screens screens = screensRepository.findById(screenseatclass.getScreens().getScreenId()).orElse(null);
//		screenseatclassdto.setScreens(screens);
		
		
		
		return seatclassdto;
//		return null;
	}

	@Override
	public SeatSetup updateSeatSet(SeatSetupDTO seatsetupDTO, int seatSetupId) {
		
		SeatSetup seatsetup = seatsetuprepository.findById(seatSetupId)
		        .orElseThrow(() -> new RuntimeException("seatsetup is  not found with ID: " + seatSetupId));
		
		seatsetup.setRowLabel(seatsetupDTO.getRowLabel());
		seatsetup.setSeat_PerRow(seatsetupDTO.getSeat_PerRow());
		seatsetup.setSeatSetupId(seatsetupDTO.getSeatSetupId());
		
		ScreenSeatClass screenseatclass = screenseatclassRepository.findById(seatsetupDTO.getScreenseatclass().getScreen_seat_type_id()).orElse(null);
		seatsetup.setScreenseatclass(screenseatclass);
		
		return seatsetup;
	}

	@Override
	public void deleteSeatSetup(int seatSetupId) {
		  SeatSetup  seatsetup = seatsetuprepository.findById(seatSetupId)
			        .orElseThrow(() -> new RuntimeException("seatsetup not found with ID: " + seatSetupId));

			  
		  seatsetuprepository.delete(seatsetup);
		
	}

//	@Override
//	public void createSeatSetupWithSeatNumbers(SeatSetup seatSetup) {
//		// TODO Auto-generated method stub
//		
//	}
	
	public void createSeatSetupWithSeatNumbers(SeatSetup seatSetup) {
	    String rowLabel = seatSetup.getRowLabel();
	    int seatCount = seatSetup.getSeat_PerRow();

	    List<SeatNumberAllocation> seatAllocations = new ArrayList<>();

	    for (int i = 1; i <= seatCount; i++) {
	        SeatNumberAllocation allocation = new SeatNumberAllocation();
	        allocation.setSeatNumber(rowLabel + i); // e.g., "A1", "A2", ...
	        allocation.setSeatsetup(seatSetup);
	        seatAllocations.add(allocation);
	    }

	    seatNumberAllocationRepository.saveAll(seatAllocations);
	}

	@Override
	public void save(SeatSetupDTO setupDTO) {
		// TODO Auto-generated method stub
		
	}

	

	 
	
	
	
	
	
	
	
	
	
	
	
	
}
