package ec.edu.service;

import java.util.List;

import ec.edu.dto.CrewAndCastDTO;
import ec.edu.model.CrewAndCast;

public interface CrewAndCastService {


	List<CrewAndCastDTO> getAllCrewAndCast();

	CrewAndCast updateCrewAndCast(int id, CrewAndCast crewandcast);

	CrewAndCastDTO getCrewAndCastById(int member_id);

	void deleteCrewAndCast(int member_id);

	CrewAndCast saveCrewAndCast(CrewAndCastDTO crewAndCastDTO);

	List<CrewAndCastDTO> getCrewAndCastByMovieId(int movieId);



}
