package ec.edu.service;

import java.util.List;

import ec.edu.dto.MovieDTO;
import ec.edu.model.Movie;

public interface MovieService {


	Movie createMovie(MovieDTO movieDTO);

	List<MovieDTO> getAllMovies();

	MovieDTO getMovieById(int movieId);

	Movie updateMovie(int movieId, MovieDTO movieDTO);

	void deleteMovie(int movieId);

	


}
