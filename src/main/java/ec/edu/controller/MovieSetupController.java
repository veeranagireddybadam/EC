package ec.edu.controller;

import ec.edu.dto.MovieSetupDto;
import ec.edu.model.MovieSetup;
import ec.edu.repository.MovieSetupRepository;
import ec.edu.service.MovieSetupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@CrossOrigin("*")
@RestController
public class MovieSetupController {

    @Autowired
    private MovieSetupService movieSetupService;
    
    @Autowired
    private MovieSetupRepository movieSetupRepository;

    // Create a new MovieSetup
    @PostMapping("/add")
    public ResponseEntity<MovieSetup> addSetup(@RequestBody MovieSetupDto dto) {
        MovieSetup created = movieSetupService.addSetup(dto);
        return ResponseEntity.ok(created);
    }

    // Get all MovieSetups
    @GetMapping("/fetchAll")
    public ResponseEntity<List<MovieSetup>> getAllSetups() {
        List<MovieSetup> setups = movieSetupService.getAllSetups();
        return ResponseEntity.ok(setups);
    }

    // Get a MovieSetup by ID
    @GetMapping("/fetchOne/{movieSetupId}")
    public ResponseEntity<MovieSetup> getSetupById(@PathVariable int movieSetupId) {
        MovieSetup setup = movieSetupService.getSetupById(movieSetupId);
        return ResponseEntity.ok(setup);
    }

    // Update an existing MovieSetup by ID
    @PutMapping("/update/{movieSetupId}")
    public ResponseEntity<MovieSetup> updateSetup(@PathVariable int movieSetupId, @RequestBody MovieSetupDto dto) {
        MovieSetup updated = movieSetupService.updateSetup(movieSetupId, dto);
        return ResponseEntity.ok(updated);
    }

    // Delete a MovieSetup by ID
    @DeleteMapping("/delete/{movieSetupId}")
    public ResponseEntity<Void> deleteSetup(@PathVariable int movieSetupId) {
        movieSetupService.deleteSetup(movieSetupId);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/check/{movieId}/{screenId}/{showDate}/{showtime}")
    public ResponseEntity<Map<String, Boolean>> checkMovieSetup(
            @PathVariable int movieId,
            @PathVariable int screenId,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date showDate,
            @PathVariable String showtime
    ) {
        boolean exists = movieSetupService.existsByDetails(movieId, screenId, showDate, showtime);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{movieId}/screens-showtimes")
    public ResponseEntity<?> getScreensAndShowtimesByMovieAndDate(
            @PathVariable("movieId") int movieId,
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {

        List<MovieSetup> setups = movieSetupService.getMovieSetupsByMovieAndDate(movieId, date);

        Map<String, Map<String, Object>> screenMap = new LinkedHashMap<>();

        for (MovieSetup setup : setups) {
            if (setup.getScreen() != null) {
                int screenId = setup.getScreen().getScreenId();
                String screenName = setup.getScreen().getScreenName();
                String showtime = setup.getShowtime();

                screenMap.computeIfAbsent(screenName, k -> {
                    Map<String, Object> screenData = new HashMap<>();
                    screenData.put("screenId", screenId);
                    screenData.put("screenName", screenName);
                    screenData.put("showtimes", new ArrayList<String>());
                    return screenData;
                });

                List<String> showtimeList = (List<String>) screenMap.get(screenName).get("showtimes");
                showtimeList.add(showtime);
            }
        }

        return ResponseEntity.ok(new ArrayList<>(screenMap.values()));
    }
    
    @GetMapping("/fetchMovieSetup")
    public ResponseEntity<?> getMovieSetupByDetails(
            @RequestParam int movieId,
            @RequestParam int screenId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date showDate,
            @RequestParam String showtime
    ) {
        Optional<MovieSetup> movieSetupOpt = movieSetupRepository.findByMovie_MovieIdAndScreen_ScreenIdAndShowDateAndShowtime(
                movieId, screenId, showDate, showtime
        );

        if (movieSetupOpt.isPresent()) {
            return ResponseEntity.ok(movieSetupOpt.get());
        } else {
            return ResponseEntity.status(404).body("Movie setup not found for provided details.");
        }
    }
    
    @GetMapping("/checkExistingSetup")
    public ResponseEntity<Map<String, Boolean>> checkExistingSetup(
    		 @RequestParam int screenId,
             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date showDate,
             @RequestParam String showtime) {

        boolean exists = movieSetupService.checkExistingSetup(screenId, showDate, showtime);

        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);

        return ResponseEntity.ok(response);
    }


}
