package ec.edu.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.edu.model.CrewAndCast;

@Repository
public interface CrewAndCastRepository extends JpaRepository<CrewAndCast, Integer> {
    List<CrewAndCast> findByMovie_MovieId(int movieId);
}
