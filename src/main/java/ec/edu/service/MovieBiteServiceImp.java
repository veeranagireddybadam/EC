package ec.edu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.dto.MovieBiteDTO;
import ec.edu.dto.ScreensDTO;
import ec.edu.model.MovieBite;
import ec.edu.model.Screens;
import ec.edu.model.User;
import ec.edu.repository.MovieBiteRepository;
import ec.edu.repository.UserRepository;

@Service 
public class MovieBiteServiceImp implements MovieBiteService {
     
	@Autowired
	private MovieBiteRepository moviebiterepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public MovieBite createMovieBite(MovieBiteDTO moviebiteDTO) {
		
		MovieBite moviebite= new MovieBite();
		moviebite.setBiteId(moviebiteDTO.getBiteId());
		moviebite.setBiteName(moviebiteDTO.getBiteName());
		moviebite.setBitePrice(moviebiteDTO.getBitePrice());
		moviebite.setMoviebiteUrl(moviebiteDTO.getMoviebiteUrl());
	//	moviebite.setCreatedBy(moviebiteDTO.getCreatedBy());
//		moviebite.setUpdatedBy(moviebiteDTO.getUpdatedBy());

	//	moviebite.setCreatedDate(moviebiteDTO.getCreatedDate());
	//	moviebite.setUpdatedDate(moviebiteDTO.getUpdatedDate());
		

		User user=userRepository.findById(moviebiteDTO.getUser().getUserId()).orElse(null);
		moviebite.setUser(user);
		
		moviebite.setCreatedBy(moviebiteDTO.getUser().getUserId());
		
		return moviebiterepository.save(moviebite);

			}

	@Override
	public List<MovieBiteDTO> allmovieBites() {
	
		ArrayList<MovieBiteDTO> moviebiteDTOs = new ArrayList<>();
		List<MovieBite> moviebite = moviebiterepository.findAll();
		
		for(MovieBite MB:moviebite) {
			
			MovieBiteDTO  moviebitedto = new MovieBiteDTO();
			
			moviebitedto.setBiteId(MB.getBiteId());
			moviebitedto.setBiteName(MB.getBiteName());
			moviebitedto.setBitePrice(MB.getBitePrice());
			moviebitedto.setMoviebiteUrl(MB.getMoviebiteUrl());		
			
			
			User user=userRepository.findById(MB.getUser().getUserId()).orElse(null);
			moviebitedto.setUser(user);
//			
			moviebitedto.setCreatedBy(MB.getUser().getUserId());
			
			

			moviebiteDTOs.add(moviebitedto);
			}
		return moviebiteDTOs;
		
	}

	@Override
	public MovieBiteDTO getonebite(int biteId) {
	    MovieBite movieBite = moviebiterepository.findById(biteId).orElse(null);

	    MovieBiteDTO dto = new MovieBiteDTO();
	    dto.setBiteId(movieBite.getBiteId());
	    dto.setBiteName(movieBite.getBiteName());
	    dto.setBitePrice(movieBite.getBitePrice());
	    dto.setMoviebiteUrl(movieBite.getMoviebiteUrl());

	    if (movieBite.getUser() != null) {
	        dto.setUser(movieBite.getUser());
	        // or set User DTO if you have one
	    } else {
	        dto.setUser(null);  // or handle as appropriate
	    }

	    return dto;
	}

	
	
	
	
	
	

	@Override
	public MovieBite updateMovieBiteDetails(MovieBiteDTO moviebitedto, int biteId) {
	    
	
//		
	
		
		MovieBite moviebite = moviebiterepository.findById(biteId).orElse(null);
	
		moviebite.setBiteName(moviebitedto.getBiteName());
		moviebite.setBitePrice(moviebitedto.getBitePrice());
		moviebite.setMoviebiteUrl(moviebitedto.getMoviebiteUrl());
		moviebite.setUpdatedBy(moviebitedto.getUser().getUserId());
		User user=userRepository.findById(moviebitedto.getUser().getUserId()).orElse(null);

		moviebite.setUser(user);
		    return moviebiterepository.save(moviebite);

		
  	}

	@Override
	public void deleteBite(int biteId) {
		moviebiterepository.deleteById(biteId);
		}

//	@Override
//	public ArrayList<MovieBiteDTO> AllmovieBites() {
//		ArrayList<MovieBiteDTO> moviebiteDTO=new ArrayList<>();
//		List<MovieBite> moviebytes = moviebiterepository.findAll();
//		
//		for(MovieBite moviebyte : moviebytes) {
//			MovieBiteDTO moviebytedtos= new MovieBiteDTO();
//			moviebytedtos.setBiteId(moviebyte.getBiteId());
//			moviebytedtos.setBiteName(moviebyte.getBiteName());
//			moviebytedtos.setBitePrice(moviebyte.getBitePrice());
//			moviebytedtos.setCreatedDate(moviebyte.getCreatedDate());
//			moviebytedtos.setUpdatedDate(moviebyte.getUpdatedDate());
//			moviebytedtos.setCreatedBy(moviebyte.getCreatedBy());
//			moviebytedtos.setUpdatedBy(moviebyte.getUpdatedBy());
//			
//			User user=userRepository.findById(moviebyte.getUser().getUserId()).orElse(null);
//			moviebytedtos.setUser(user);
//			
//			moviebytedtos.setCreatedBy(moviebyte.getUser().getUserId());
//			
//			
//				moviebiteDTO.add(moviebytedtos);
//			
//		}
//	  return moviebiteDTO;
//	
//	}

}