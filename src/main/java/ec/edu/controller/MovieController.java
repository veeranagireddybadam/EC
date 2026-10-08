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

import ec.edu.dto.MovieDTO;
import ec.edu.model.Movie;
import ec.edu.service.MovieService;

@RestController
@CrossOrigin
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/saveMovie")
    public ResponseEntity<Movie> createMovie(@RequestBody MovieDTO movieDTO) {
        Movie movie = movieService.createMovie(movieDTO);
        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }

    @GetMapping("/fetchAllMovies")
    public ResponseEntity<List<MovieDTO>> fetchAllMovies() {
    	List<MovieDTO> moviedtos=movieService.getAllMovies();
        return new ResponseEntity<>(moviedtos, HttpStatus.OK);
    }

    @GetMapping("/fetchMovie/{movieId}")
    public ResponseEntity<MovieDTO> fetchMovie(@PathVariable int movieId) {
    	MovieDTO movieDTO=movieService.getMovieById(movieId);
        return new ResponseEntity<>(movieDTO, HttpStatus.OK);
    }

    @PutMapping("/updateMovie/{movieId}")
    public ResponseEntity<Movie> updateMovie(@PathVariable int movieId, @RequestBody MovieDTO movieDTO) {
        Movie movie = movieService.updateMovie(movieId, movieDTO);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @DeleteMapping("/deleteMovie/{movieId}")
    public ResponseEntity<String> deleteMovie(@PathVariable int movieId) {
        movieService.deleteMovie(movieId);
        return new ResponseEntity<>("Deleted movie with ID " + movieId, HttpStatus.OK);
    }
}

