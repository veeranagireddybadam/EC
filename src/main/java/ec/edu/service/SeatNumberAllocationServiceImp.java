package ec.edu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.dto.SeatNumberAllocationDTO;
import ec.edu.model.ScreenSeatClass;
import ec.edu.model.SeatNumberAllocation;
import ec.edu.model.SeatSetup;
import ec.edu.repository.SeatNumberAllocationRepository;
import ec.edu.repository.SeatSetupRepository;

@Service
public class SeatNumberAllocationServiceImp implements SeatNumberAllocationService {
	@Autowired
	private SeatNumberAllocationRepository seatnumberallocationrepository;
	@Autowired
	private SeatSetupRepository seatsetuprepository;
	
	@Override
	public SeatNumberAllocation createSeatNumber(SeatNumberAllocationDTO seatnumberallocationDTO) {
		
	     SeatNumberAllocation seatnumberallocation = new SeatNumberAllocation();

	        seatnumberallocation.setSeatNumber(seatnumberallocationDTO.getSeatNumber());
//	        seatnumberallocation.setSeatStatus(seatnumberallocationDTO.getSeatStatus());

	        SeatSetup seatsetup = seatsetuprepository.findById(seatnumberallocationDTO.getSeatsetup().getSeatSetupId()).orElse(null);

	        if (seatsetup == null) {
	            throw new IllegalArgumentException("SeatSetup not found for ID: " + seatnumberallocationDTO.getSeatsetup().getSeatSetupId());
	        }

	        seatnumberallocation.setSeatsetup(seatsetup);

	        return seatnumberallocationrepository.save(seatnumberallocation);
	  
	}
	


	@Override
	public List<SeatNumberAllocationDTO> fetchAllSeatNumber() {
    ArrayList<SeatNumberAllocationDTO> seatnumberallocationdto = new ArrayList<>();
   
    List<SeatNumberAllocation>seatnumberallocation =seatnumberallocationrepository.findAll();
    
    
    for(SeatNumberAllocation seatnumberallocate:seatnumberallocation) {
    
    	SeatNumberAllocationDTO seatnumberallocatedto = new  SeatNumberAllocationDTO();
    
     	seatnumberallocatedto.setSeatNumber(seatnumberallocate.getSeatNumber());
      	
//     	seatnumberallocatedto.setSeatStatus(seatnumberallocate.getSeatStatus());
     	
    	seatnumberallocatedto.setSeatNumberAllocationId(seatnumberallocate.getSeatNumberAllocationId());
    	
	SeatSetup seatsetup = seatsetuprepository.findById(seatnumberallocate.getSeatsetup().getSeatSetupId()).orElse(null);
	
	
	
	seatnumberallocatedto.setSeatsetup(seatsetup);
    	
//      SeatSetup seatsetup = seatsetuprepository.findById(seatnumberallocate.getSeatsetup().getSeatSetupId()).orElse(null);
//		
//      seatnumberallocatedto.setSeatsetup(seatsetup);
    	
      seatnumberallocationdto.add(seatnumberallocatedto);
      
      
    }
    
    	return seatnumberallocationdto;
	}


	@Override
	public List<SeatNumberAllocation> generateSeatsFromSetup(SeatSetup seatsetup) {
	    List<SeatNumberAllocation> seatNumbers = new ArrayList<>();

	    for (int i = 1; i <= seatsetup.getSeat_PerRow(); i++) {
	        SeatNumberAllocation seat = new SeatNumberAllocation();
	        seat.setSeatNumber(seatsetup.getRowLabel() + i); 
	        seat.setSeatsetup(seatsetup);
	        
	        seatNumbers.add(seat);
	   }
	return seatNumbers;
	}


	@Override
	public List<SeatNumberAllocation> generateSeatNumbers(SeatSetup seatSetup) {
		   List<SeatNumberAllocation> allocations = new ArrayList<>();

	        for (int i = 1; i <= seatSetup.getSeat_PerRow(); i++) {
	            SeatNumberAllocation seatNumberAllocation = new SeatNumberAllocation();
	            seatNumberAllocation.setSeatNumber(seatSetup.getRowLabel() + i); 
	            seatNumberAllocation.setSeatsetup(seatSetup);
	            allocations.add(seatNumberAllocation);
	        }  
		
		
		return seatnumberallocationrepository.saveAll(allocations);
	}


	public void generateSeatsForScreenSeatClass(ScreenSeatClass seatClass, int seatPerRow) {
	    List<SeatSetup> setups = seatsetuprepository.findByScreenseatclass(seatClass);

	    for (SeatSetup setup : setups) {
	        String rowLabel = setup.getRowLabel();
	        for (int i = 1; i <= seatPerRow; i++) {
	            SeatNumberAllocation seat = new SeatNumberAllocation();
	            seat.setSeatNumber(rowLabel);  
	            seat.setSeatsetup(setup); 
	            seatnumberallocationrepository.save(seat);
	        }
	    }
	}  

	@Override
	public void save(SeatNumberAllocationDTO seatNumberDTO) {
		// TODO Auto-generated method stub
		
	}
}
