package ec.edu.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.dto.ScreenFullDataDTO;
import ec.edu.dto.ScreenSeatClassDTO;
import ec.edu.dto.ScreensDTO;
import ec.edu.dto.SeatSetupDTO;
import ec.edu.model.ScreenSeatClass;
import ec.edu.model.Screens;
import ec.edu.model.SeatNumberAllocation;
import ec.edu.model.SeatSetup;
import ec.edu.model.User;
import ec.edu.repository.ScreenSeatClassRepository;
import ec.edu.repository.ScreensRepository;
import ec.edu.repository.SeatNumberAllocationRepository;
import ec.edu.repository.SeatSetupRepository;
import ec.edu.repository.UserRepository;

@Service
public class ScreensServiceImp implements ScreensService {
	
	
	@Autowired
	private UserRepository userrepository;
	
	@Autowired
	private ScreensRepository screensrepository;

	
	@Autowired
	private ScreenSeatClassRepository screenseatclassrepository;
	
	@Autowired
	private SeatSetupRepository seatsetuprepository;
	
	@Autowired
	private SeatNumberAllocationRepository seatnumberallocationrepository;
	

	
	@Autowired
	private ScreenSeatClassService screenseatclassService;
	
	@Autowired
	private SeatSetupService seatsetupservice;
	
	@Autowired
	private SeatNumberAllocationService seatnumberallocationservice;
	

	
	

	@Override
	public Screens createScreen(ScreensDTO screensdto) {
		Screens screens =   new Screens();
		
		
		screens.setScreenName(screensdto.getScreenName());
		screens.setSeatingCapacity(screensdto.getSeatingCapacity());
		User user=userrepository.findById(screensdto.getUser().getUserId()).orElse(null);
		screens.setUser(user);
		screensrepository.save(screens);

		
		return screens;
		}

	@Override
	public List<ScreensDTO> fetchallScreens() {

		ArrayList<ScreensDTO> screensDTOs = new ArrayList<>();
		List<Screens> screens = screensrepository.findAll();
		
		for(Screens screen:screens) {
			
			ScreensDTO  screensdto = new ScreensDTO();
			
			screensdto.setScreenId(screen.getScreenId());
			screensdto.setScreenName(screen.getScreenName());
			screensdto.setSeatingCapacity(screen.getSeatingCapacity());
			
//			User user=userrepository.findById(screen.getUser().getUserId()).orElse(null);
//			screensdto.setUser(user);
//			screensdto.setUser(screen.getUser());
			
			screensDTOs.add(screensdto);
			}
		return screensDTOs;
	
	}

	@Override
	public ScreensDTO getonescreen(int screenId) {
		Screens screens = screensrepository.findById(screenId).orElse(null);
		ScreensDTO screensDTOs=new ScreensDTO();
		
		screensDTOs.setScreenId(screens.getScreenId());
		screensDTOs.setScreenName(screens.getScreenName());
		screensDTOs.setSeatingCapacity(screens.getSeatingCapacity());
		
//		User user=userrepository.findById(screens.getUser().getUserId()).orElse(null);
//		screensDTOs.setUser(user);
		
//		screensDTOs.setUser(screens.getUser());
//		
		return screensDTOs;

	}

	@Override
	public Screens updateScreen(ScreensDTO screensDTO, int screenId) {
		Screens screens = screensrepository.findById(screenId)
		        .orElseThrow(() -> new RuntimeException("Screens not found with ID: " + screenId));
		
		
		
		screens.setScreenName(screensDTO.getScreenName());
		screens.setSeatingCapacity(screensDTO.getSeatingCapacity());
//		User user=userrepository.findById(screensDTO.getUser().getUserId()).orElse(null);
//		screens.setUser(user);
  
		    return screensrepository.save(screens);

		}

	@Override
	public Screens updateScreenDetails(ScreensDTO screensdto, int screenId) {
	    Screens screens = screensrepository.findById(screenId).orElse(null);
	    if (screens == null) {
	        throw new RuntimeException("Screen with ID " + screenId + " not found");
	    }

	 
	    screens.setScreenName(screensdto.getScreenName());
	    screens.setSeatingCapacity(screensdto.getSeatingCapacity());
	   
	   List<ScreenSeatClass> seatClassList = new ArrayList<>();
	        if (screensdto.getScreenseatclass() != null) {
	            for (ScreenSeatClassDTO seatClassDto : screensdto.getScreenseatclass()) {
	                ScreenSeatClass seatClass = new ScreenSeatClass();
	                seatClass.setScreen_seat_type_id(seatClassDto.getScreen_seat_type_id());
	                seatClass.setSeat_class(seatClassDto.getSeat_class());
	                seatClass.setTotal_seat_count(seatClassDto.getTotal_seat_count());
	                seatClass.setPrice_per_seat(seatClassDto.getPrice_per_seat());
	                seatClass.setScreens(screens);

//	                List<SeatSetup> seatSetups = new ArrayList<>();
//	                List<SeatSetupDTO> seatsetupdtos = seatClassDto.getSeatsetup();
//	                if (seatsetupdtos!= null) {
//	                	
//	                    for (SeatSetupDTO seatSetupDto :seatsetupdtos ) {
//	                        SeatSetup setup = new SeatSetup();
//	                        setup.setRowLabel(seatSetupDto.getRowLabel());
//	                        setup.setSeat_PerRow(seatSetupDto.getSeat_PerRow());
//	                        seatSetups.add(setup);
//	                    }
//	                }

//	                seatClass.setSeatsetup(seatSetups);
	               // seatClassList.add(seatClass);
	            }
	        }

	        screens.getScreenseatclass().clear();
	        screens.getScreenseatclass().addAll(seatClassList);

	        return screensrepository.save(screens);
//		return null;
	    

	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    

	    
//	    List<ScreenSeatClass> seatClassList = Optional.ofNullable(screensdto.getScreenseatclass())
//	        .orElse(null)
//	        .stream()
//	        .map(seatClassDto -> {
//	           
//	        	ScreenSeatClass seatClass = new ScreenSeatClass();
//	            seatClass.setScreen_seat_type_id(seatClassDto.getScreen_seat_type_id());
//	            seatClass.setSeat_class(seatClassDto.getSeat_class());
//	            seatClass.setTotal_seat_count(seatClassDto.getTotal_seat_count());
//	            seatClass.setPrice_per_seat(seatClassDto.getPrice_per_seat());
//
//	            
//	            List<SeatSetup> seatSetups = Optional.ofNullable(seatClassDto.getSeatsetup())
//	                .orElse(null)
//	                .stream()
//	                .map(seatSetupDto -> {
//	                    SeatSetup setup = new SeatSetup();
//	                    setup.setRowLabel(seatSetupDto.getRowLabel());
//	                    
//	                    setup.setSeat_PerRow(seatSetupDto.getSeat_PerRow());
//	                    
////	                    setup.setSeat_count(seatSetupDto.getSeat_count());
////	                    setup.setSeatClass(seatClass); 
//	                    return setup;
//	                })
//	                .toList();
//
//	            seatClass.setSeatsetup(seatSetups);
//	            seatClass.setScreens(screens); 
//	            return seatClass;
//	        })
//	        .toList();

//	    
//	    screens.getScreenseatclass().clear();
//	    screens.getScreenseatclass().addAll(seatClassList);

	   
//	    return screensrepository.save(screens);
//	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@Override
	public void deleteScreen(int screenId) {
	    Screens  screens = screensrepository.findById(screenId)
		        .orElseThrow(() -> new RuntimeException("screens not found with ID: " + screenId));

		    // Delete movie
	    screensrepository.delete(screens);
		}

	@Override
	public ScreensDTO getbyname(String screenName) {
		Screens screens = screensrepository.findByScreenName(screenName).orElse(null);
		ScreensDTO screensDTOs=new ScreensDTO();
		
		screensDTOs.setScreenId(screens.getScreenId());
		screensDTOs.setScreenName(screens.getScreenName());
		screensDTOs.setSeatingCapacity(screens.getSeatingCapacity());
		
//		User user=userrepository.findById(screens.getUser().getUserId()).orElse(null);
//		screensDTOs.setUser(user);
		
//		screensDTOs.setUser(screens.getUser());
//		
		return screensDTOs; 
		
		
	}
	
	
//	@Override
//	public ScreensDTO getscreendetails(int screenId) {
//	    Screens screens = screensrepository.findById(screenId).orElse(null);
//	    if (screens == null) return null;
//
//	    ScreensDTO screenDTO = new ScreensDTO();
//	    screenDTO.setScreenId(screens.getScreenId());
//	    screenDTO.setScreenName(screens.getScreenName());
//	    screenDTO.setSeatingCapacity(screens.getSeatingCapacity());
//
//	    List<ScreenSeatClassDTO> seatClassDTOs = screens.getScreenseatclass()
//	        .stream()
//	        .map(seatClass -> {
//	        	ScreenSeatClassDTO seatClassDTO = new ScreenSeatClassDTO();
//	            seatClassDTO.setScreen_seat_type_id(seatClass.getScreen_seat_type_id());
//	            seatClassDTO.setSeat_class(seatClass.getSeat_class());
//	            
//	            seatClassDTO.setPrice_per_seat(seatClass.getPrice_per_seat());
//
//	            List<SeatSetupDTO> seatseteupDTO = seatClass.getSeatsetup()
//	                .stream()
//	                .map(row -> {
//	                	SeatSetupDTO  rowDTO = new SeatSetupDTO();
//	                    rowDTO.setRowLabel(row.getRowLabel());
//	                    
//	                    rowDTO.setSeat_PerRow(row.getSeat_PerRow());
//	                   
//	                    return rowDTO;
//	                    
//	                    
////	                    seatClassDTO.setSeatsetup(rowDTO);    
//	                })
//	                .toList();
//
//            seatClassDTO.setSeatsetup(rowDTO);
//	            return seatClassDTO;
//	        })
//	        .toList();
//
//	    screenDTO.setScreenseatclass(seatClassDTOs);
//
//	    return screenDTO;
//	}

	
	
	
	
	@Override
	public ScreensDTO getscreendetails(int screenId) {
	    Screens screens = screensrepository.findById(screenId).orElse(null);

	    if (screens == null) return null;

	    ScreensDTO screensDTOs = new ScreensDTO();
	    screensDTOs.setScreenId(screens.getScreenId());
	    screensDTOs.setScreenName(screens.getScreenName());
	    screensDTOs.setSeatingCapacity(screens.getSeatingCapacity());

	    // Map List<ScreenSeatClass> to List<ScreenSeatClassDTO>
	    List<ScreenSeatClassDTO> seatClassDTOs = screens.getScreenseatclass()
	        .stream()
	        .map(seatClass -> {
	            ScreenSeatClassDTO dto = new ScreenSeatClassDTO();
	            dto.setScreen_seat_type_id(seatClass.getScreen_seat_type_id());
	            dto.setSeat_class(seatClass.getSeat_class());
	            dto.setTotal_seat_count(seatClass.getTotal_seat_count());
	            dto.setPrice_per_seat(seatClass.getPrice_per_seat());
//	          
	            
	            
	            
	            
	            
//           dto.setSeatsetup(seatClass.getSeatsetup())
	            
            //    dto.setSeatsetup(seatClass.getPrice_per_seat());
	            
                return dto;
	        })
	        .toList();

	    screensDTOs.setScreenseatclass(seatClassDTOs);

	    return screensDTOs;
	}

	@Override
	public void saveScreenData(ScreenFullDataDTO screenFullDataDTO) {
        
//		Screens screen = screenservice.save(screenFullDataDTO.getScreen());
//
//		screenFullDataDTO.getSeatClasses().forEach(seatClassDTO -> {
//			  seatClassDTO.setScreenId(screen.getScreenId());
//            screenseatclassService.save(seatClassDTO);
//        });
//
//		screenFullDataDTO.getSeatSetups().forEach(setupDTO -> {
//			setupDTO.setScreenId(screen.getScreenId());
//            seatsetupservice.save(setupDTO);
//        });
//
//		screenFullDataDTO.getSeatNumberAllocations().forEach(seatNumberDTO -> {
//			seatNumberDTO.setScreenId(screen.getScreenId());
//			seatnumberallocationservice.save(seatNumberDTO);
//        });

	
	
	}

	@Override
	public void updateScreenSeatPrices(ScreensDTO screensDTO, int screenId) {
		
	      Screens existingSeatClasses = screensrepository.findById(screenId).orElse(null);

	     
	        Map<Integer, Double> updatedPrices = screensDTO.getScreenseatclass().stream()
	            .collect(Collectors.toMap(
	                ScreenSeatClassDTO::getScreen_seat_type_id,
	                ScreenSeatClassDTO::getPrice_per_seat
	            ));
	        
	        

//	    
//	        for (Screens seatClass : existingSeatClasses) {
//	            if (updatedPrices.containsKey(seatClass.getScreen_seat_type_id())) {
//	                seatClass.setPrice_per_seat(updatedPrices.get(seatClass.getScreen_seat_type_id()));
//	            }
//	        }

	  
	       // screensrepository.saveAll(existingSeatClasses);
	    }

//	@Override
//	public Screens createAllScreen(ScreensDTO screensdto) {
//	
//		Screens screens = new Screens();
//		
//	screens.setScreenName(screensdto.getScreenName());
//	screens.setSeatingCapacity(screensdto.getSeatingCapacity());
//	
//	User user=userrepository.findById(screensdto.getUser().getUserId()).orElse(null);
//	screens.setUser(user);
//	
//	
//		
//		
//		
//		
//		return null;
//	}

	
	@Override
	public Screens createFullScreen(ScreensDTO dto) {
		   Screens screen = new Screens();
	        screen.setScreenName(dto.getScreenName());
	        screen.setSeatingCapacity(dto.getSeatingCapacity());
	        screen.setUser(userrepository.findById(dto.getUser().getUserId()).orElseThrow());

	        Screens savedScreen = screensrepository.save(screen);
		
	        for (ScreenSeatClassDTO classDTO : dto.getScreenseatclass()) {
	            ScreenSeatClass ssc = new ScreenSeatClass();
	            ssc.setSeat_class(classDTO.getSeat_class());
	            ssc.setTotal_seat_count(classDTO.getTotal_seat_count());
	            ssc.setPrice_per_seat(classDTO.getPrice_per_seat());
	           
	            ssc.setScreens(savedScreen);

	            ScreenSeatClass savedClass = screenseatclassrepository.save(ssc);
	        
	            for (SeatSetupDTO seatSetupDTO :  classDTO.getSeatsetups() ) {
	                SeatSetup setup = new SeatSetup();
	                setup.setRowLabel(seatSetupDTO.getRowLabel());
	                setup.setSeat_PerRow(seatSetupDTO.getSeat_PerRow());
	                setup.setScreens(savedScreen);
	                setup.setScreenseatclass(savedClass);

	                SeatSetup savedSetup = seatsetuprepository.save(setup);

	           
	                for (int i = 1; i <= seatSetupDTO.getSeat_PerRow(); i++) {
	                    SeatNumberAllocation seat = new SeatNumberAllocation();
	                    seat.setSeatNumber(seatSetupDTO.getRowLabel() + String.format("%02d", i));
//	                    seat.setSeatStatus("AVAILABLE");
	                    seat.setSeatsetup(savedSetup);
	                    seatnumberallocationrepository.save(seat);
	                }
	            }    
	            
	            
	            
	            
	        } 
	        
	        
	        
		
		return null;
	}
		
	

	

	
	

//	@Override
//	public ScreensDTO getscreendetails(int screenId) {
//		
//		
//		Screens screens = screensrepository.findById(screenId).orElse(null);
//		ScreensDTO screensDTOs=new ScreensDTO();
//		
//		screensDTOs.setScreenId(screens.getScreenId());
//		screensDTOs.setScreenName(screens.getScreenName());
//		screensDTOs.setSeatingCapacity(screens.getSeatingCapacity());
//		
//		
//		
////		ScreenSeatClass screenseatclass = screenseatclassrepository.findById(screens.getScreenseatclass());
//		
//		 screensDTOs.setScreenseatclass(screens.getScreenId());
//		
////		screensDTOs.setScreenseatclass(screenseatclass);
////		
//		
//		
////		User user=userrepository.findById(screens.getUser().getUserId()).orElse(null);
////		screensDTOs.setUser(user);
//		
//		
//			return screensDTOs;
//	}

//	@Override
//	public ScreensDTO getbyname(String screenName) {
//		
//		List<ScreensDTO> screensDTOs = new ArrayList<>();
//		
//		List<Screens> screens = screensrepository.findByScreenName(screenName);
//		
//   for(Screens s:screens){
//	   
//	   ScreensDTO screensdto = new ScreensDTO();
//		
//	   screensdto.setScreenId(s.getScreenId());
//	   screensdto.setScreenName(s.getScreenName());
//	   screensdto.setSeatingCapacity(s.getSeatingCapacity());
//		
//	    User user=userrepository.findById(s.getUser().getUserId()).orElse(null);
//	    screensdto.setUser(user);
//   }
//  
//		return screensdto;
//		
//		
//		
//		
//		
//		
////		Screens screens = screensrepository.findByScreenName(screenName).orElse(null);
////		ScreensDTO screensDTOs=new ScreensDTO();
////		
////		screensDTOs.setScreenId(screens.getScreenId());
////		screensDTOs.setScreenName(screens.getScreenName());
////		screensDTOs.setSeatingCapacity(screens.getSeatingCapacity());
////		
////		User user=userrepository.findById(screens.getUser().getUserId()).orElse(null);
////		screensDTOs.setUser(user);
////		return screensDTOs;
//	}

	
	
	
	
	
//	Screens screens = screensrepository.findById(screenId).orElse(null);
//	ScreensDTO screensDTOs=new ScreensDTO();
//	
//	screensDTOs.setScreenId(screens.getScreenId());
//	screensDTOs.setScreenName(screens.getScreenName());
//	screensDTOs.setSeatingCapacity(screens.getSeatingCapacity());
//	
//	User user=userrepository.findById(screens.getUser().getUserId()).orElse(null);
//	screensDTOs.setUser(user);
	
	
	
	
	
	
	
	
	
	

		
	}


