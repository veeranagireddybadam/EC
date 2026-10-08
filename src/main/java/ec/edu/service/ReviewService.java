package ec.edu.service;

import java.util.List;

import ec.edu.dto.ReviewDTO;
import ec.edu.model.Review;

public interface ReviewService {

	Review createMovieReview(ReviewDTO reviewDTO);

	List<ReviewDTO> fetchallMovieReview();

	ReviewDTO getoneMovieReview(int reviewId);

	Review updateMovieReview(ReviewDTO reviewDTO, int reviewId);

	void deleteMovieReview(int reviewId);

	Object findByMovie_MovieId(int movieId);

//	Object getReviewsByMovie(int movieId);

//	Object findByMovie_MovieId(int movieId);

}