package ec.edu.service;

import ec.edu.dto.MovieSetupDto;
import ec.edu.model.Movie;
import ec.edu.model.MovieSetup;
import ec.edu.model.Screens;
import ec.edu.model.User;
import ec.edu.repository.MovieRepository;
import ec.edu.repository.MovieSetupRepository;
import ec.edu.repository.ScreensRepository;
import ec.edu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MovieSetupServiceImpl implements MovieSetupService {

    @Autowired
    private MovieSetupRepository movieSetupRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ScreensRepository screenRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public MovieSetup addSetup(MovieSetupDto dto) {
        Movie movie = movieRepository.findById(dto.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + dto.getMovieId()));

        Screens screen = screenRepository.findById(dto.getScreenId())
                .orElseThrow(() -> new RuntimeException("Screen not found with id: " + dto.getScreenId()));

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + dto.getUserId()));

        MovieSetup movieSetup = new MovieSetup();
        movieSetup.setMovie(movie);
        movieSetup.setScreen(screen);
        movieSetup.setShowDate(dto.getShowDate());
        movieSetup.setShowtime(dto.getShowtime());
        movieSetup.setUser(user);

        return movieSetupRepository.save(movieSetup);
    }

    @Override
    public List<MovieSetup> getAllSetups() {
        return movieSetupRepository.findAll();
    }

    @Override
    public MovieSetup getSetupById(int movieSetupId) {
        return movieSetupRepository.findById(movieSetupId)
                .orElseThrow(() -> new RuntimeException("MovieSetup not found with id: " + movieSetupId));
    }

    @Override
    public MovieSetup updateSetup(int movieSetupId, MovieSetupDto dto) {
        MovieSetup existingSetup = getSetupById(movieSetupId);

        Movie movie = movieRepository.findById(dto.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + dto.getMovieId()));

        Screens screen = screenRepository.findById(dto.getScreenId())
                .orElseThrow(() -> new RuntimeException("Screen not found with id: " + dto.getScreenId()));

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + dto.getUserId()));

        existingSetup.setMovie(movie);
        existingSetup.setScreen(screen);
        existingSetup.setShowDate(dto.getShowDate());
        existingSetup.setShowtime(dto.getShowtime());
        existingSetup.setUser(user);

        return movieSetupRepository.save(existingSetup);
    }

    @Override
    public void deleteSetup(int movieSetupId) {
        MovieSetup existingSetup = getSetupById(movieSetupId);
        movieSetupRepository.delete(existingSetup);
    }

    public boolean existsByDetails(int movieId, int screenId, Date showDate, String showtime) {
        return movieSetupRepository.existsByMovie_MovieIdAndScreen_ScreenIdAndShowDateAndShowtime(
            movieId, screenId, showDate, showtime
        );
    }
    
    @Override
    public List<MovieSetup> getMovieSetupsByMovieAndDate(int movieId, Date date) {
        return movieSetupRepository.findByMovieMovieIdAndShowDate(movieId, date);
    }
    
    public boolean checkExistingSetup(int screenId, Date showDate, String showtime) {
        return movieSetupRepository.existsByScreen_ScreenIdAndShowDateAndShowtime(screenId, showDate, showtime);
    }
    

}
