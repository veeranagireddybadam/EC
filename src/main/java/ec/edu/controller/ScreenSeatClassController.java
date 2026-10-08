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
import ec.edu.dto.ScreensDTO;
import ec.edu.model.ScreenSeatClass;
import ec.edu.service.ScreenSeatClassService;

@CrossOrigin("*")
@RestController
public class ScreenSeatClassController {
  
	@Autowired
	 private ScreenSeatClassService screenseatclassservice;
	
	@PostMapping("/savescreenseat")
	public ResponseEntity<ScreenSeatClass>createScreenSeatClass(@RequestBody ScreenSeatClassDTO screenseatclassDTO){
		ScreenSeatClass screenseatclass= screenseatclassservice.createScreenSeatClass(screenseatclassDTO);
		return new ResponseEntity <> (screenseatclass,HttpStatus.OK);
	}

	@GetMapping("/fetchallScreenSeatClass")
	public ResponseEntity<List<ScreenSeatClassDTO>> fetchallScreenSeatClass(){
		List<ScreenSeatClassDTO> screenseatclassDTOs=screenseatclassservice.fetchallScreenSeatClass();
		return new ResponseEntity<>(screenseatclassDTOs,HttpStatus.OK);
		
		}
	@GetMapping("/fetchoneScreenSeatClass/{screen_seat_type_id}")
	public ResponseEntity<ScreenSeatClassDTO> getoneScreenSeatClass(@PathVariable int screen_seat_type_id){
		ScreenSeatClassDTO screenseatclassDTOs=screenseatclassservice.getoneScreenSeatClass(screen_seat_type_id);
		return new ResponseEntity<> (screenseatclassDTOs,HttpStatus.OK);

	}
	@PutMapping("/updateScreenSeatClass/{screen_seat_type_id}")
	public ResponseEntity<ScreenSeatClass> updateScreenSeatClass(@RequestBody ScreenSeatClassDTO screenseatclassDTO,  @PathVariable int screen_seat_type_id){
		ScreenSeatClass screenseatclass= screenseatclassservice.updateScreenSeatClass(screenseatclassDTO,screen_seat_type_id);
		return new ResponseEntity<>(screenseatclass,HttpStatus.OK);

	}
	
	
	
//	 @DeleteMapping("/deleteScreenSeatClass/{screen_seat_type_id}"){
//	    public ResponseEntity<String> deleteScreenSeatClass(@PathVariable int screen_seat_type_id){
//		 screenseatclassservice.deleteScreen(screen_seat_type_id);
//			  return new ResponseEntity<>("Deleted screenseatclass with Id" +screen_seat_type_id,HttpStatus.OK);
//		  }
//
//	
//	 }
	@DeleteMapping("/deleteScreenSeatClass/{screen_seat_type_id}")
	public ResponseEntity<String> deleteScreenSeatClass(@PathVariable int screen_seat_type_id ){
		screenseatclassservice.deleteScreenSeatClass(screen_seat_type_id);
		return new ResponseEntity<>("Deleted screenseatclass with Id" +screen_seat_type_id,HttpStatus.OK);
	}
		
    @PutMapping("/updateScreenSeatprices/{screenId}")
    public ResponseEntity<String> updateScreenSeatPrices(@RequestBody ScreensDTO screensDTO, @PathVariable int screenId ) {
    	screenseatclassservice.updateScreenSeatPrices(screensDTO,screenId);
        return ResponseEntity.ok("Prices updated successfully.");
    }
    
    @PutMapping("/update-prices/{screenId}")
    public ResponseEntity<String> updatePricesByScreenId(
            @PathVariable int screenId,
            @RequestBody List<ScreenSeatClass> updates
    ) {
    	screenseatclassservice.updatePricesByScreenId(screenId, updates);
        return ResponseEntity.ok("Prices updated successfully.");
    }

	

	
	
}
