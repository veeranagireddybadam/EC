package ec.edu.service;

import ec.edu.dto.MovieSetupDto;
import ec.edu.model.MovieSetup;

import java.util.Date;
import java.util.List;

public interface MovieSetupService {

	MovieSetup addSetup(MovieSetupDto dto);


	List<MovieSetup> getAllSetups();

	MovieSetup getSetupById(int movieSetupId);

	MovieSetup updateSetup(int movieSetupId, MovieSetupDto dto);

	void deleteSetup(int movieSetupId);


	boolean existsByDetails(int movieId, int screenId, Date showDate, String showtime);

    List<MovieSetup> getMovieSetupsByMovieAndDate(int movieId, Date date);


	boolean checkExistingSetup(int screenId, Date showDate, String showtime);


//	MovieSetup getById(int movieSetupId);

//	List<MovieSetup> getMovieSetupsByMovieAndDate(int movieId, Date date);
}
