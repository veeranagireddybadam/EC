package ec.edu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.dto.ScreenManagementDTO;
import ec.edu.dto.ScreenManagementWithBookingDTO;
import ec.edu.dto.ScreenSeatClassDTO;
import ec.edu.dto.ScreensDTO;
import ec.edu.dto.SeatNumberAllocationDTO;
import ec.edu.dto.SeatSetupDTO;
import ec.edu.model.ScreenSeatClass;
import ec.edu.model.Screens;
import ec.edu.model.SeatNumberAllocation;
import ec.edu.model.SeatSetup;
import ec.edu.model.User;
import ec.edu.repository.BookingTicketNumberRepository;
import ec.edu.repository.ScreenSeatClassRepository;
import ec.edu.repository.ScreensRepository;
import ec.edu.repository.SeatNumberAllocationRepository;
import ec.edu.repository.SeatSetupRepository;
import ec.edu.repository.UserRepository;

@Service
public class ScreenManagementServiceImp implements ScreenManagementService {

	
    @Autowired
    private ScreensRepository screensRepository;
    @Autowired 
    private ScreenSeatClassRepository seatClassRepository;
    @Autowired
    private SeatSetupRepository seatSetupRepository;
    @Autowired 
    private SeatNumberAllocationRepository seatAllocationRepository;
    @Autowired 
    private UserRepository userRepository;
    
    @Autowired
    private BookingTicketNumberRepository bookingTicketNumberRepository;

    
    
    
    @Override
	public void saveAll(ScreenManagementDTO dto) {
		
        Screens screen = new Screens();
        screen.setScreenName(dto.getScreen().getScreenName());
        screen.setSeatingCapacity(dto.getScreen().getSeatingCapacity());

       
    	User user=userRepository.findById(dto.getScreen().getUser().getUserId()).orElse(null);
		screen.setUser(user);

        Screens savedScreen = screensRepository.save(screen);

       
//        List<ScreenSeatClass> savedSeatClasses = new ArrayList<>();
       
        for (ScreenSeatClassDTO seatClassDTO : dto.getSeatClasses()) {
            ScreenSeatClass seatClass = new ScreenSeatClass();
            seatClass.setSeat_class(seatClassDTO.getSeat_class());
            seatClass.setTotal_seat_count(seatClassDTO.getTotal_seat_count());
            seatClass.setPrice_per_seat(seatClassDTO.getPrice_per_seat());
            
            
           seatClass.setScreens(savedScreen);
          
            ScreenSeatClass saveSeatClass=seatClassRepository.save(seatClass);
         
            for (SeatSetupDTO setupDTO : seatClassDTO.getSeatsetups()) {
              
            	SeatSetup setupEntity = new SeatSetup();
                setupEntity.setRowLabel(setupDTO.getRowLabel());
                setupEntity.setSeat_PerRow(setupDTO.getSeat_PerRow());
//                
//             Screens  screens = screensRepository.findById(setupDTO.getScreens().getScreenId()).orElse(null);  
//             setupEntity.setScreens(screens);
//             
                setupEntity.setScreenseatclass(saveSeatClass);
                SeatSetup savedSetup = seatSetupRepository.save(setupEntity);
              
                List<SeatNumberAllocationDTO> allocations = setupDTO.getSeatnumberallocation();
                

                if (allocations == null || allocations.isEmpty()) {
                    for (int i = 1; i <= setupDTO.getSeat_PerRow(); i++) {
                        SeatNumberAllocation allocation = new SeatNumberAllocation();
                        allocation.setSeatNumber(setupDTO.getRowLabel() + i);
//                        allocation.setSeatStatus("available");
                        allocation.setSeatsetup(savedSetup);
                        seatAllocationRepository.save(allocation);
                    }
                } else {
                 
                    for (SeatNumberAllocationDTO allocationDTO : allocations) {
                        SeatNumberAllocation allocation = new SeatNumberAllocation();
                        allocation.setSeatNumber(allocationDTO.getSeatNumber());
//                        allocation.setSeatStatus(allocationDTO.getSeatStatus());
                        allocation.setSeatsetup(savedSetup);
                        seatAllocationRepository.save(allocation);
                    }
                }
            }
        }
}
    
    
    
    @Override
    public void updateAll(ScreenManagementDTO dto, int screenId) {

        // Fetch and update Screen details
        Screens screen = screensRepository.findById(dto.getScreen().getScreenId())
                .orElseThrow(() -> new RuntimeException("Screen not found"));

        screen.setScreenName(dto.getScreen().getScreenName());
        screen.setSeatingCapacity(dto.getScreen().getSeatingCapacity());

        if (dto.getScreen().getUser().getUserId() != 0) {
            userRepository.findById(dto.getScreen().getUser().getUserId())
                    .ifPresent(screen::setUser);
        }

        Screens updatedScreen = screensRepository.save(screen);

        // Process each Seat Class
        for (ScreenSeatClassDTO seatClassDTO : dto.getSeatClasses()) {

            ScreenSeatClass seatClass;

            // Existing or new
            if (seatClassDTO.getScreen_seat_type_id() != 0) {
                seatClass = seatClassRepository.findById(seatClassDTO.getScreen_seat_type_id())
                        .orElseThrow(() -> new RuntimeException("SeatClass not found"));
            } else {
                seatClass = new ScreenSeatClass();
                seatClass.setScreens(updatedScreen);
            }

            // Common updates for both existing and new
            seatClass.setSeat_class(seatClassDTO.getSeat_class());
            seatClass.setTotal_seat_count(seatClassDTO.getTotal_seat_count());
            seatClass.setPrice_per_seat(seatClassDTO.getPrice_per_seat());

            seatClass = seatClassRepository.save(seatClass);

            // Process Seat Setups inside each Seat Class
            for (SeatSetupDTO seatSetupDTO : seatClassDTO.getSeatsetups()) {
                SeatSetup seatSetup;

                // Existing or new
                if (seatSetupDTO.getSeatSetupId() != 0) {
                    seatSetup = seatSetupRepository.findById(seatSetupDTO.getSeatSetupId())
                            .orElseThrow(() -> new RuntimeException("SeatSetup not found"));
                } else {
                    seatSetup = new SeatSetup();
                    seatSetup.setScreens(updatedScreen);
                }

                // Update Seat Setup details
                seatSetup.setRowLabel(seatSetupDTO.getRowLabel());
                seatSetup.setSeat_PerRow(seatSetupDTO.getSeat_PerRow());
                seatSetup.setScreenseatclass(seatClass);

                SeatSetup savedSeatSetup = seatSetupRepository.save(seatSetup);

                // Process Seat Number Allocations for this SeatSetup
                List<SeatNumberAllocationDTO> allocations = seatSetupDTO.getSeatnumberallocation();

                if (allocations == null || allocations.isEmpty()) {
                    // If no allocations provided — regenerate based on seat count
                    for (int i = 1; i <= seatSetupDTO.getSeat_PerRow(); i++) {
                        SeatNumberAllocation allocation = new SeatNumberAllocation();
                        allocation.setSeatNumber(seatSetupDTO.getRowLabel() + i);
                        allocation.setSeatsetup(savedSeatSetup);
                        seatAllocationRepository.save(allocation);
                    }
                } else {
                    // If allocations provided — update or add them
                    for (SeatNumberAllocationDTO allocDTO : allocations) {
                        SeatNumberAllocation allocation;

                        if (allocDTO.getSeatNumberAllocationId() != 0) {
                            allocation = seatAllocationRepository.findById(allocDTO.getSeatNumberAllocationId())
                                    .orElse(new SeatNumberAllocation());
                        } else {
                            allocation = new SeatNumberAllocation();
                        }

                        allocation.setSeatNumber(allocDTO.getSeatNumber());
                        allocation.setSeatsetup(savedSeatSetup);

                        seatAllocationRepository.save(allocation);
                    }
                }
            }
        }
    }




	@Override
	public ScreenManagementDTO getScreenManagementById(int screenId) {
		  Screens screen = screensRepository.findById(screenId)
		            .orElseThrow(() -> new RuntimeException("Screen not found"));

		    ScreenManagementDTO dto = new ScreenManagementDTO();


		   ScreensDTO screenDTO =ScreensDTO.builder()
		            .screenId(screen.getScreenId())
		            .screenName(screen.getScreenName())
		            .seatingCapacity(screen.getSeatingCapacity())
		            .user(screen.getUser()) 
		            .build();
		   dto.setScreen(screenDTO);
		
		    List<ScreenSeatClassDTO> seatClassDTOs = new ArrayList<>();
		    List<ScreenSeatClass> seatClasses = seatClassRepository.findByScreens(screen);
		    for (ScreenSeatClass seatClass : seatClasses) {
		    	List<SeatSetup> seatSetups = seatSetupRepository.findByScreenseatclass(seatClass);
		        List<SeatSetupDTO> seatSetupDTOList = new ArrayList<>();

		        for (SeatSetup setup : seatSetups) {
		          
		            List<SeatNumberAllocation> allocations = seatAllocationRepository.findBySeatsetup(setup);
		            List<SeatNumberAllocationDTO> allocationDTOs = new ArrayList<>();

		            for (SeatNumberAllocation allocation : allocations) {
		                allocationDTOs.add(SeatNumberAllocationDTO.builder()
		                        .seatNumberAllocationId(allocation.getSeatNumberAllocationId())
		                        .seatNumber(allocation.getSeatNumber())
//		                        .seatStatus(allocation.getSeatStatus())
		                        .build());
		            }

		            seatSetupDTOList.add(SeatSetupDTO.builder()
		                    .seatSetupId(setup.getSeatSetupId())
		                    .rowLabel(setup.getRowLabel())
		                    .seat_PerRow(setup.getSeat_PerRow())
		                    .seatnumberallocation(allocationDTOs)
		                    .build());
		        }

		       
		        seatClassDTOs.add(ScreenSeatClassDTO.builder()
		                .screen_seat_type_id(seatClass.getScreen_seat_type_id())
		                .seat_class(seatClass.getSeat_class())
		                .total_seat_count(seatClass.getTotal_seat_count())
		                .price_per_seat(seatClass.getPrice_per_seat())
		                .seatsetups(seatSetupDTOList)
		                .build());
		    }

    dto.setSeatClasses(seatClassDTOs);
		

		    return dto;
	}
	
	
	
	public ScreenManagementWithBookingDTO getScreenManagementWithBookings(int screenId, int movieSetupId) {
	    Screens screen = screensRepository.findById(screenId)
	            .orElseThrow(() -> new RuntimeException("Screen not found"));

	    // Existing seat layout logic
	    ScreenManagementDTO layoutDTO = getScreenManagementById(screenId);

	    // Get booked seat allocation IDs for the MovieSetup
	    List<Integer> bookedSeatAllocationIds = bookingTicketNumberRepository.findBookedSeatAllocationIdsByMovieSetupId(movieSetupId);

	    // Return combined DTO
	    return new ScreenManagementWithBookingDTO(layoutDTO, bookedSeatAllocationIds);
	}

}

	
	
	
	
	
	
	
	
	
	
	
	
	
	

