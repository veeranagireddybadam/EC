package ec.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.edu.model.MovieBite;
@Repository
public interface MovieBiteRepository extends JpaRepository<MovieBite, Integer>{

}
