package ec.edu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.dto.ReviewDTO;
import ec.edu.dto.ScreensDTO;
import ec.edu.model.Review;
import ec.edu.repository.ReviewRepository;
import ec.edu.service.ReviewService;

@RestController
@CrossOrigin("*")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private ReviewRepository  reviewRepository;
	
	
	
	
	
	@PostMapping("/API/MovieReview")
	public ResponseEntity<Review>createMovieReview(@RequestBody ReviewDTO reviewDTO){
		Review review=reviewService.createMovieReview(reviewDTO);
		return new ResponseEntity<>(review,HttpStatus.CREATED) ;
	}
	
	@GetMapping("/API/fetchallMovieReview")
	public ResponseEntity<List<ReviewDTO>>fetchallMovieReview(){
		List<ReviewDTO> reviewDTOs = reviewService.fetchallMovieReview();
		return new ResponseEntity(reviewDTOs,HttpStatus.OK);
		
	}
	
	@GetMapping("/API/fetchbyoneMovieReview/{reviewId}")
		public ResponseEntity<ReviewDTO>getoneMovieReview(@PathVariable int reviewId){
		ReviewDTO reviewDTOs=reviewService.getoneMovieReview(reviewId);
		return new ResponseEntity<>(reviewDTOs,HttpStatus.OK);

	}
	
	@PutMapping("/API/updateMovieReview/{reviewId}")
	public ResponseEntity<Review>updateMovieReview(@RequestBody ReviewDTO reviewDTO, @PathVariable int reviewId){
		Review review=reviewService.updateMovieReview(reviewDTO,reviewId);
		return new ResponseEntity<>(review, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/API/deleteMovieReview/{reviewId}")
	public ResponseEntity<String>deleteMovieReview(@PathVariable int reviewId){
		reviewService.deleteMovieReview(reviewId);
		return new ResponseEntity<>("Delete review with Id" +reviewId,HttpStatus.OK);
		
	}
	
	
	
	@GetMapping("/moviereview/{movieId}")
	public ResponseEntity<List<Review>> getReviewsByMovieId(@PathVariable int movieId) {
	    List<Review> reviews = reviewRepository.findByMovie_MovieId(movieId);
	    return ResponseEntity.ok(reviews);
	}
	}