package ec.edu.repository;

import ec.edu.model.MovieSetup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Repository
public interface MovieSetupRepository extends JpaRepository<MovieSetup, Integer> {
    List<MovieSetup> findByMovie_MovieId(int movieId);
    
    boolean existsByMovie_MovieIdAndScreen_ScreenIdAndShowDateAndShowtime(
            int movieId, int screenId, Date showDate, String showtime
        );
    List<MovieSetup> findByMovieMovieIdAndShowDate(int movieId, Date showDate);
    Optional<MovieSetup> findByMovie_MovieIdAndScreen_ScreenIdAndShowDateAndShowtime(int movieId, int screenId, Date showDate, String showtime);

    // Check if any movie setup exists for given screen, date, and time
    boolean existsByScreen_ScreenIdAndShowDateAndShowtime(int screenId, Date showDate, String showtime);
    
    
}
