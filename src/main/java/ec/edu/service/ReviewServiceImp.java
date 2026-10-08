package ec.edu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.dto.BookingTicketDTO;
import ec.edu.dto.ReviewDTO;
import ec.edu.model.BookingTicket;
import ec.edu.model.Movie;
import ec.edu.model.Review;
import ec.edu.model.Screens;
import ec.edu.model.User;
import ec.edu.repository.MovieRepository;
import ec.edu.repository.ReviewRepository;
import ec.edu.repository.UserRepository;


@Service
public class ReviewServiceImp implements ReviewService {
	
	@Autowired
	private    ReviewRepository   reviewRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MovieRepository movieRepository;
	

	@Override
	public Review createMovieReview(ReviewDTO reviewDTO) {
		 User user = userRepository.findById(reviewDTO.getUser().getUserId())
		            .orElseThrow(() -> new RuntimeException("User not found with id: " + reviewDTO.getUser().getUserId()));

		    Movie movie = movieRepository.findById(reviewDTO.getMovie().getMovieId())
		            .orElseThrow(() -> new RuntimeException("Movie not found with id: " + reviewDTO.getMovie().getMovieId()));


	    Review review = new Review();
	    //review.setMovieTitle(reviewDTO.getMovieTitle());
	    review.setRating(reviewDTO.getRating());
	    review.setReview(reviewDTO.getReview());
	    review.setMovie(movie);
	    review.setUser(user);

	    return reviewRepository.save(review);
	}



	@Override
	public List<ReviewDTO> fetchallMovieReview() {
	    List<ReviewDTO> reviewDTOs = new ArrayList<>();
	    List<Review> reviews = reviewRepository.findAll();

	    for (Review review : reviews) {
	        ReviewDTO reviewDTO = new ReviewDTO();
	        reviewDTO.setReviewId(review.getReviewId());
	        //reviewDTO.setMovieTitle(review.getMovieTitle());
	        reviewDTO.setRating(review.getRating());
	        reviewDTO.setReview(review.getReview());
	        reviewDTO.setUser(review.getUser());
	        reviewDTO.setMovie(review.getMovie());
	        
	        
	        
	        

	        reviewDTOs.add(reviewDTO);
	    }
	    return reviewDTOs;
	}


	@Override
	public ReviewDTO getoneMovieReview(int reviewId) {
		Review reviews = reviewRepository.findById(reviewId).orElse(null);
		ReviewDTO reviewDTO = new ReviewDTO();
		reviewDTO.setReviewId(reviews.getReviewId());
		//reviewDTO.setMovieTitle(reviews.getMovieTitle());
		reviewDTO.setRating(reviews.getRating());
		reviewDTO.setMovie(reviews.getMovie());
		reviewDTO.setUser(reviews.getUser());
		return reviewDTO;
	}
	


	@Override
	public Review updateMovieReview(ReviewDTO reviewDTO, int reviewId) {
		Review reviews = reviewRepository.findById(reviewId).orElse(null);
		reviews.setReviewId(reviewDTO.getReviewId());
		//reviews.setMovieTitle(reviewDTO.getMovieTitle());
		reviews.setRating(reviewDTO.getRating());
		reviews.setMovie(reviewDTO.getMovie());
		reviews.setUser(reviewDTO.getUser());
		return reviewRepository.save(reviews);
	}


	@Override
	public void deleteMovieReview(int reviewId) {
	
		reviewRepository.deleteById(reviewId);
	}



	@Override
	public Object findByMovie_MovieId(int movieId) {
	
		return reviewRepository.findByMovie_MovieId(movieId);
	}
	

}