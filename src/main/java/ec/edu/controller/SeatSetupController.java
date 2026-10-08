package ec.edu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.dto.ScreenSeatClassDTO;
import ec.edu.dto.SeatSetupDTO;
import ec.edu.model.ScreenSeatClass;
import ec.edu.model.SeatSetup;
import ec.edu.service.SeatSetupService;

@CrossOrigin("*")
@RestController
public class SeatSetupController {
	
	@Autowired
	private SeatSetupService seatsetupservice;
	
	@PostMapping("/saveseatsetup")
	public ResponseEntity<SeatSetup>createSeatRow(@RequestBody SeatSetupDTO seatsetupDTO){
		SeatSetup seatsetup = seatsetupservice.createSeatRow(seatsetupDTO);
		return new ResponseEntity <> (seatsetup,HttpStatus.OK);
	}
	
	@GetMapping("/getallseatsetup")
	public ResponseEntity<List<SeatSetupDTO>> fetchallSeatRow(){
		List<SeatSetupDTO> seatrowDTOs=seatsetupservice.fetchallSeatRow();
		return new ResponseEntity<>(seatrowDTOs,HttpStatus.OK);
		
		}
	
	@GetMapping("/fetchoneSeatsetup/{seatSetupId}")
	
	public ResponseEntity<SeatSetupDTO> getoneSeatssetup(@PathVariable int seatSetupId){
		SeatSetupDTO seatsetupDTOs=seatsetupservice.getoneSeatssetup(seatSetupId);
		return new ResponseEntity<> (seatsetupDTOs,HttpStatus.OK);

	}
	@PutMapping("/updateSeatsetup/{seatSetupId}")
	public ResponseEntity<SeatSetup> updateSeatSet(@RequestBody SeatSetupDTO seatsetupDTO,  @PathVariable int seatSetupId){
		SeatSetup seatsetup= seatsetupservice.updateSeatSet(seatsetupDTO,seatSetupId);
		return new ResponseEntity<>(seatsetup,HttpStatus.OK);

	}
	
	@DeleteMapping("/DeleteSeatSetup{seatSetupId}")
	public ResponseEntity<String> deleteSeatSetup(@PathVariable int seatSetupId){
		seatsetupservice.deleteSeatSetup(seatSetupId); 
		    return new ResponseEntity<>("Deleted seatSetup with ID: " + seatSetupId, HttpStatus.OK);
		}
	
	
	
//	 	@PostMapping("/createwithseats")
//	    public ResponseEntity<String> createSeatSetupWithSeats(@RequestBody SeatSetup seatSetup) {
//		 seatsetupservice.createSeatSetupWithSeatNumbers(seatSetup);
//	        return ResponseEntity.ok("Seat setup and seat numbers created successfully");
//	    }
	
	
	
	
	
	
	
       
} 
