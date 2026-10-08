package ec.edu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.dto.MovieDTO;
import ec.edu.model.Movie;
import ec.edu.model.User;
import ec.edu.repository.MovieRepository;
import ec.edu.repository.UserRepository;

@Service
public class MovieServiceImp implements MovieService{
	
	@Autowired
    private  MovieRepository movieRepository;
	@Autowired
	private  UserRepository userRepository;
	@Override
	public Movie createMovie(MovieDTO movieDTO) {
	    Movie movie = new Movie();

	    movie.setMovieTitle(movieDTO.getMovieTitle());
	    movie.setGenre(movieDTO.getGenre());
	    movie.setLanguage(movieDTO.getLanguage());
	    movie.setDuration(movieDTO.getDuration());
	    movie.setReleaseDate(movieDTO.getReleaseDate());
	    movie.setDescription(movieDTO.getDescription());
	    movie.setPosterUrl(movieDTO.getPosterUrl());
	    movie.setCreatedBy(movieDTO.getUser().getUserId());
	    movie.setUpdatedBy(movieDTO.getUser().getUserId());
	    
	    User user =userRepository.findById(movieDTO.getUser().getUserId()).orElse(null);
	    movie.setUser(user);

	    return movieRepository.save(movie);
	}
	@Override
	public List<MovieDTO> getAllMovies() {
	    List<Movie> movies = movieRepository.findAll();
	    List<MovieDTO> movieDTOs = new ArrayList<>();

	    for (Movie movie : movies) {
	        MovieDTO dto = new MovieDTO();
	        dto.setMovieId(movie.getMovieId());
	        dto.setMovieTitle(movie.getMovieTitle());
	        dto.setGenre(movie.getGenre());
	        dto.setLanguage(movie.getLanguage());
	        dto.setDuration(movie.getDuration());
	        dto.setReleaseDate(movie.getReleaseDate());
	        dto.setDescription(movie.getDescription());
	        dto.setPosterUrl(movie.getPosterUrl());
	        dto.setCreatedBy(movie.getCreatedBy());
	        dto.setUpdatedBy(movie.getUpdatedBy());
	        dto.setUpdatedDate(movie.getUpdatedDate());
	        dto.setCreatedDate(movie.getCreatedDate());
		    User user =userRepository.findById(movie.getUser().getUserId()).orElse(null);

		    dto.setUser(user);

	        movieDTOs.add(dto);
	    }

	    return movieDTOs;
	}

	@Override
	public MovieDTO getMovieById(int movieId) {
	    Movie movie = movieRepository.findById(movieId)
	        .orElseThrow(() -> new RuntimeException("Movie not found with ID: " + movieId));

	    MovieDTO dto = new MovieDTO();
	    dto.setMovieId(movie.getMovieId());
	    dto.setMovieTitle(movie.getMovieTitle());
	    dto.setGenre(movie.getGenre());
	    dto.setLanguage(movie.getLanguage());
	    dto.setDuration(movie.getDuration());
	    dto.setReleaseDate(movie.getReleaseDate());
	    dto.setPosterUrl(movie.getPosterUrl());
        dto.setDescription(movie.getDescription());

	    dto.setCreatedBy(movie.getCreatedBy());
	    dto.setUpdatedBy(movie.getUpdatedBy());
	    User user =userRepository.findById(movie.getUser().getUserId()).orElse(null);

	    dto.setUser(user);
	    return dto;
	}

	@Override
	public Movie updateMovie(int movieId, MovieDTO movieDTO) {
	    // Fetch existing movie by ID
	    Movie movie = movieRepository.findById(movieId)
	        .orElseThrow(() -> new RuntimeException("Movie not found with ID: " + movieId));

	    // Update fields from DTO
	    movie.setMovieTitle(movieDTO.getMovieTitle());
	    movie.setGenre(movieDTO.getGenre());
	    movie.setLanguage(movieDTO.getLanguage());
	    movie.setDuration(movieDTO.getDuration());
	    movie.setReleaseDate(movieDTO.getReleaseDate());
	    movie.setPosterUrl(movieDTO.getPosterUrl());
	    movie.setDescription(movieDTO.getDescription());

	    movie.setUser(movieDTO.getUser());

	    movie.setUpdatedBy(movieDTO.getUpdatedBy());

	   
	    return movieRepository.save(movie);
	}

	@Override
	public void deleteMovie(int movieId) {
	    // Check if movie exists before deleting
	    Movie movie = movieRepository.findById(movieId)
	        .orElseThrow(() -> new RuntimeException("Movie not found with ID: " + movieId));

	    // Delete movie
	    movieRepository.delete(movie);
	}


}
