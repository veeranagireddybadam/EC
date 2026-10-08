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

import ec.edu.dto.MovieBiteDTO;
import ec.edu.dto.ScreensDTO;
import ec.edu.model.MovieBite;
import ec.edu.model.Screens;
import ec.edu.service.MovieBiteService;

@CrossOrigin("*")
@RestController
public class MovieBiteController {

    @Autowired
	private  MovieBiteService moviebiteService;
    
	 	@PostMapping("/savemoviebite")
	    public ResponseEntity<MovieBite> createMovieBite(@RequestBody MovieBiteDTO moviebiteDTO) {
		 MovieBite moviebyte=moviebiteService.createMovieBite(moviebiteDTO);
		 
	        return new ResponseEntity<>(moviebyte,HttpStatus.CREATED);
	         }

	 	@GetMapping("/allmoviebites")
	 	public ResponseEntity<List<MovieBiteDTO>> allmovieBites() {
	 	    List<MovieBiteDTO> movieBites = moviebiteService.allmovieBites();
	 	    return new ResponseEntity<>(movieBites, HttpStatus.OK);
	 	}
	 	
		
		@GetMapping("/fetchonebite/{biteId}")
		public ResponseEntity<MovieBiteDTO> getonebite(@PathVariable int biteId){
			MovieBiteDTO movieBitesdto=moviebiteService.getonebite(biteId);
			return new ResponseEntity<> (movieBitesdto,HttpStatus.OK);

		} 
		@DeleteMapping("/deleteBite/{biteId}")
		public ResponseEntity<String> deleteBite(@PathVariable int biteId) {
		    moviebiteService.deleteBite(biteId);
		    return new ResponseEntity<>("Deleted bite with Id " + biteId, HttpStatus.OK);
		}

	 	
		@PutMapping("/updateMovieBiteDetails/{biteId}")
		public ResponseEntity<MovieBite> updateMovieBiteDetails (@RequestBody MovieBiteDTO moviebitedto, @PathVariable int biteId){
			MovieBite movieBite = moviebiteService.updateMovieBiteDetails(moviebitedto,biteId);
			return new ResponseEntity<>(movieBite,HttpStatus.OK);
			
		}	
		
		
		
}
