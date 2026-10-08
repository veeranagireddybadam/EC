package ec.edu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.edu.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer> {

	List<Review> findByMovie_MovieId(int movieId);



	

}