package ec.edu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.dto.CrewAndCastDTO;
import ec.edu.model.CrewAndCast;
import ec.edu.model.Movie;
import ec.edu.repository.CrewAndCastRepository;
import ec.edu.repository.MovieRepository;

@Service
public class CrewAndCastServiceImp implements CrewAndCastService {

    @Autowired
    private CrewAndCastRepository crewandcastRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public CrewAndCast saveCrewAndCast(CrewAndCastDTO crewAndCastDTO) {
        CrewAndCast crewAndCast = new CrewAndCast();
        crewAndCast.setName(crewAndCastDTO.getName());
        crewAndCast.setRole(crewAndCastDTO.getRole());
        crewAndCast.setMember_type(crewAndCastDTO.getMember_type());
        crewAndCast.setDepartment(crewAndCastDTO.getDepartment());
        crewAndCast.setPhoto_url(crewAndCastDTO.getPhoto_url());

        // Fetch and set associated movie
        Movie movie = movieRepository.findById(crewAndCastDTO.getMovie().getMovieId()).orElse(null);
        crewAndCast.setMovie(movie);

        return crewandcastRepository.save(crewAndCast);
    }

    @Override
    public List<CrewAndCastDTO> getAllCrewAndCast() {
        List<CrewAndCastDTO> crewAndCastDTOs = new ArrayList<>();
        List<CrewAndCast> crewAndCasts = crewandcastRepository.findAll();

        for (CrewAndCast crewAndCast : crewAndCasts) {
            CrewAndCastDTO dto = new CrewAndCastDTO();
            dto.setMember_id(crewAndCast.getMember_id());
            dto.setName(crewAndCast.getName());
            dto.setRole(crewAndCast.getRole());
            dto.setMember_type(crewAndCast.getMember_type());
            dto.setDepartment(crewAndCast.getDepartment());
            dto.setPhoto_url(crewAndCast.getPhoto_url());
            dto.setMovie(crewAndCast.getMovie());

            crewAndCastDTOs.add(dto);
        }
        return crewAndCastDTOs;
    }

    @Override
    public CrewAndCastDTO getCrewAndCastById(int member_id) {
        CrewAndCast crewAndCast = crewandcastRepository.findById(member_id).orElse(null);

        if (crewAndCast == null) return null;

        CrewAndCastDTO dto = new CrewAndCastDTO();
        dto.setMember_id(crewAndCast.getMember_id());
        dto.setName(crewAndCast.getName());
        dto.setRole(crewAndCast.getRole());
        dto.setMember_type(crewAndCast.getMember_type());
        dto.setDepartment(crewAndCast.getDepartment());
        dto.setPhoto_url(crewAndCast.getPhoto_url());
        dto.setMovie(crewAndCast.getMovie());

        return dto;
    }

    @Override
    public CrewAndCast updateCrewAndCast(int member_id, CrewAndCast updated) {
        CrewAndCast existing = crewandcastRepository.findById(member_id).orElse(null);

        if (existing == null) return null;

        existing.setName(updated.getName());
        existing.setRole(updated.getRole());
        existing.setMember_type(updated.getMember_type());
        existing.setDepartment(updated.getDepartment());
        existing.setPhoto_url(updated.getPhoto_url());
        existing.setMovie(updated.getMovie());

        return crewandcastRepository.save(existing);
    }

    @Override
    public void deleteCrewAndCast(int member_id) {
        crewandcastRepository.deleteById(member_id);
    }

	@Override
	public List<CrewAndCastDTO> getCrewAndCastByMovieId(int movieId) {
		List<CrewAndCastDTO> crewAndCastDTOs = new ArrayList<>();
        List<CrewAndCast> crewAndCasts = crewandcastRepository.findByMovie_MovieId(movieId);

        for (CrewAndCast crewAndCast : crewAndCasts) {
            CrewAndCastDTO dto = new CrewAndCastDTO();
            dto.setMember_id(crewAndCast.getMember_id());
            dto.setName(crewAndCast.getName());
            dto.setRole(crewAndCast.getRole());
            dto.setMember_type(crewAndCast.getMember_type());
            dto.setDepartment(crewAndCast.getDepartment());
            dto.setPhoto_url(crewAndCast.getPhoto_url());
            dto.setMovie(crewAndCast.getMovie());

            crewAndCastDTOs.add(dto);
        }
		return crewAndCastDTOs;
	}

//    public List<CrewAndCast> getCrewAndCastByMovieId(int movieId) {
//        return crewAndCastRepository.findByMovie_MovieId(movieId);
//    }


	

}
