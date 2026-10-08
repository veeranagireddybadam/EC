package ec.edu.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ec.edu.dto.CrewAndCastDTO;
import ec.edu.model.CrewAndCast;
import ec.edu.service.CrewAndCastService;

@CrossOrigin("*")
@RestController
public class CrewAndCastController {

    @Autowired
    private CrewAndCastService crewandcastService;

    @PostMapping("/saveCrewAndCast")
    public ResponseEntity<CrewAndCast> createCrewAndCast(@RequestBody CrewAndCastDTO crewAndCastDTO) {
        CrewAndCast savedCrewAndCast = crewandcastService.saveCrewAndCast(crewAndCastDTO);
        return new ResponseEntity<>(savedCrewAndCast, HttpStatus.CREATED);
    }

    @GetMapping("/fetchallCrewAndCast")
    public ResponseEntity<List<CrewAndCastDTO>> getAllCrewAndCast() {
        List<CrewAndCastDTO> crewAndCastList = crewandcastService.getAllCrewAndCast();
        return new ResponseEntity<>(crewAndCastList, HttpStatus.OK);
    }

    @GetMapping("/fetchByCrewAndCast/{member_id}")
    public ResponseEntity<CrewAndCastDTO> getCrewAndCastById(@PathVariable int member_id) {
        CrewAndCastDTO crewandcastDTO = crewandcastService.getCrewAndCastById(member_id);
        return new ResponseEntity<>(crewandcastDTO, HttpStatus.OK);
    }

    @PutMapping("/updatefetchCrewAndCast/{member_id}")
    public ResponseEntity<CrewAndCast> updateCrewAndCast(@PathVariable int member_id, @RequestBody CrewAndCast crewandcast) {
        CrewAndCast updatedCrewAndCast = crewandcastService.updateCrewAndCast(member_id, crewandcast);
        return new ResponseEntity<>(updatedCrewAndCast, HttpStatus.OK);
    }

    @DeleteMapping("/API/DeleteCrewAndCastService/{member_id}")
    public ResponseEntity<String> deleteCrewAndCast(@PathVariable int member_id) {
        crewandcastService.deleteCrewAndCast(member_id);
        return new ResponseEntity<>("deleted with crewandcast id: " + member_id, HttpStatus.OK);
    }

    @GetMapping("/movies/{movieId}/crewAndCast")
    public ResponseEntity<List<CrewAndCastDTO>> getCrewAndCastByMovie(@PathVariable int movieId) {
        List<CrewAndCastDTO> crewAndCastList = crewandcastService.getCrewAndCastByMovieId(movieId);
        return new ResponseEntity<>(crewAndCastList, HttpStatus.OK);
    }
}
