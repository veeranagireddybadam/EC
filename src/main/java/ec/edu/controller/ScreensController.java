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

import ec.edu.dto.ScreenFullDataDTO;
import ec.edu.dto.ScreensDTO;

import ec.edu.model.Screens;
import ec.edu.service.ScreenSeatClassService;
import ec.edu.service.ScreensService;

@CrossOrigin("*")
@RestController
public class ScreensController {
	@Autowired
	 private ScreensService screenservice;
	
	@Autowired
	private ScreenSeatClassService screenseatclassservice; 
	
	@PostMapping("/saveScreen")
	public ResponseEntity<Screens> createScreen(@RequestBody ScreensDTO Screensdto){
		Screens screens = screenservice.createScreen(Screensdto);
		
		return new ResponseEntity <> (screens,HttpStatus.CREATED );
	} 
       

	@GetMapping("/fetchScreenDetails/{screenId}")
	public ResponseEntity<ScreensDTO> getscreendetails(@PathVariable int screenId){
		ScreensDTO screensDTOs=screenservice.getscreendetails(screenId);
		return new ResponseEntity<> (screensDTOs,HttpStatus.OK);

	}
	
	
  @GetMapping("/fetchallscreen")
	public ResponseEntity<List<ScreensDTO>> fetchallScreens(){
		List<ScreensDTO> screensDTOs=screenservice.fetchallScreens();
		return new ResponseEntity<>(screensDTOs,HttpStatus.OK);
		
		}
	
	@GetMapping("/fetchonescreen/{screenId}")
	public ResponseEntity<ScreensDTO> getonescreen(@PathVariable int screenId){
		ScreensDTO screensDTOs=screenservice.getonescreen(screenId);
		return new ResponseEntity<> (screensDTOs,HttpStatus.OK);

	}
	
	@PutMapping("/updateScreenDatails/{screenId}")
	public ResponseEntity<Screens> updateScreenDetails (@RequestBody ScreensDTO screensdto, @PathVariable int screenId){
		Screens screens = screenservice.updateScreenDetails(screensdto,screenId);
		return new ResponseEntity<>(screens,HttpStatus.OK);
		
		
		
	}	
	
	@PutMapping("/updateScreen/{screenId}")
	public ResponseEntity<Screens> updateScreen(@RequestBody ScreensDTO screensDTO,  @PathVariable int screenId){
		Screens screens= screenservice.updateScreen(screensDTO,screenId);
		return new ResponseEntity<>(screens,HttpStatus.OK);

	}
	
	  @DeleteMapping("/deleteScreen/{screenId}")
	  
  public ResponseEntity<String> deleteScreen(@PathVariable int screenId){
		  screenservice.deleteScreen(screenId);
		  return new ResponseEntity<>("Deleted screen with Id" +screenId,HttpStatus.OK);
	  }
	  
		@GetMapping("/fetchbyname/{screenName}")
		public ResponseEntity<ScreensDTO> getbyname(@PathVariable int screenName){
			ScreensDTO screensDTOs=screenservice.getonescreen(screenName);
			return new ResponseEntity<> (screensDTOs,HttpStatus.OK);

		} 
	  

        @PostMapping("/savefullscreen")
public ResponseEntity<String> saveScreenData(@RequestBody ScreenFullDataDTO screenFullDataDTO) {
        	screenservice.saveScreenData(screenFullDataDTO);
    return ResponseEntity.ok("Saved successfully");
}

   
     
        
    	@PostMapping("/saveFullScreen")
    	public ResponseEntity<Screens> createFullScreen(@RequestBody ScreensDTO Screensdto){
    		Screens screens = screenservice.createFullScreen(Screensdto);
    		
    		return new ResponseEntity <> (screens,HttpStatus.CREATED );
    	} 
                 
        
        
        
        
        
        
        

}










