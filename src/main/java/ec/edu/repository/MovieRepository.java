package ec.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.edu.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>{

}
