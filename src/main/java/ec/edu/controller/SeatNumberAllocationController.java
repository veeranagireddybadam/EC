package ec.edu.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.dto.SeatNumberAllocationDTO;
import ec.edu.dto.SeatSetupDTO;
import ec.edu.model.ScreenSeatClass;
import ec.edu.model.SeatNumberAllocation;
import ec.edu.model.SeatSetup;
import ec.edu.repository.ScreenSeatClassRepository;
import ec.edu.repository.SeatNumberAllocationRepository;
import ec.edu.repository.SeatSetupRepository;
import ec.edu.service.SeatNumberAllocationService;
import ec.edu.service.SeatSetupService;

@RestController
@CrossOrigin("*")
public class SeatNumberAllocationController {
     
	@Autowired
	private SeatNumberAllocationService seatnumberallocationservice;
	@Autowired
	private SeatSetupRepository seatsetuprepository; 
	@Autowired
	private  SeatSetupService seatSetupService;
	
	@Autowired
    private ScreenSeatClassRepository seatClassRepository;
	
	@Autowired
	private SeatNumberAllocationRepository seatNumberAllocationRepository;

  
	
	@PostMapping("/saveseatnumber")
	public ResponseEntity<SeatNumberAllocation> createSeatNumber(@RequestBody SeatNumberAllocationDTO seatnumberallocationDTO){
		SeatNumberAllocation  seatnumberallocation = seatnumberallocationservice.createSeatNumber(seatnumberallocationDTO);
		return new ResponseEntity <> (seatnumberallocation,HttpStatus.OK);
	}
	
	@PostMapping("/generateseats/{seatSetupId}")
	public ResponseEntity<List<SeatNumberAllocation>> generateSeats(@PathVariable int seatSetupId) {
	 
		
		SeatSetup setup = seatsetuprepository.findById(seatSetupId).orElse(null);
	                         

	    List<SeatNumberAllocation> seats = seatnumberallocationservice.generateSeatsFromSetup(setup);
	    return ResponseEntity.ok(seats);
	}
	

//    @PostMapping("/create-with-seats")
//    public ResponseEntity<String> createSeatSetupWithSeats(@RequestBody SeatSetup seatSetup) {
//        seatSetupService.createSeatSetupWithSeatNumbers(seatSetup);
//        return ResponseEntity.ok("Seat setup and seat numbers created successfully");
//    }
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/fetchAllSeatNumber")
	public ResponseEntity<List<SeatNumberAllocationDTO>> fetchAllSeatNumber(){
		
		List<SeatNumberAllocationDTO> seatnumberallocationDTOs=seatnumberallocationservice.fetchAllSeatNumber();
		return new ResponseEntity<>(seatnumberallocationDTOs,HttpStatus.OK);
		
		
	}
	
	
	   @PostMapping("/generate")
	    public ResponseEntity<List<SeatNumberAllocation>> generate(@RequestBody SeatSetup seatSetup) {
	        List<SeatNumberAllocation> seats = seatnumberallocationservice.generateSeatNumbers(seatSetup);
	        return ResponseEntity.ok(seats);
	    }
	   
	   
	   
	   @PostMapping("/generate/{seatClassId}")
	    public ResponseEntity<String> generateSeats(
	            @PathVariable Integer seatClassId,
	            @RequestParam int seatPerRow) {

	        Optional<ScreenSeatClass> optionalClass = seatClassRepository.findById(seatClassId);
	        if (optionalClass.isPresent()) {
	        	seatnumberallocationservice.generateSeatsForScreenSeatClass(optionalClass.get(), seatPerRow);
	            return ResponseEntity.ok("Seats generated successfully.");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Seat class not found.");
	        }
	    }
	   
	   @GetMapping("/getSeatNumberById/{seatNumberAllocationId}")
	   public ResponseEntity<String> getSeatNumberById(@PathVariable int seatNumberAllocationId) {
	       Optional<SeatNumberAllocation> seatOpt = seatNumberAllocationRepository.findById(seatNumberAllocationId);
	       if (seatOpt.isPresent()) {
	           return ResponseEntity.ok(seatOpt.get().getSeatNumber());
	       } else {
	           return ResponseEntity.notFound().build();
	       }
	   }

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
