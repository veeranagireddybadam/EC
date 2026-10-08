package ec.edu.dto;

import ec.edu.model.Movie;
import ec.edu.model.User;
import lombok.Data;

@Data
public class ReviewDTO {

    private int reviewId;
    
//    private String movieTitle;
    
    private float rating;
    
    private String review;
    
    private User user;
    
    private Movie movie;
	}