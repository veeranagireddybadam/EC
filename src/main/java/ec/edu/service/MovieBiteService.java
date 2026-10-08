package ec.edu.service;

import java.util.List;

import ec.edu.dto.MovieBiteDTO;
import ec.edu.model.MovieBite;

public interface MovieBiteService {

	MovieBite createMovieBite(MovieBiteDTO moviebiteDTO);

	List<MovieBiteDTO> allmovieBites();

	MovieBiteDTO getonebite(int biteId);

	MovieBite updateMovieBiteDetails(MovieBiteDTO moviebitedto, int biteId);

	void deleteBite(int biteId);
	
}