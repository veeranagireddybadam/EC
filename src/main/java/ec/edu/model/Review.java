package ec.edu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Review {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewId;

//    @Column(name = "movie_title")
//    private String movieTitle;

    @Column(name = "rating")
    private float rating;
    
    @Column(name="review")
    private String review;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

//    @ManyToOne
//    @JoinColumn(name="movie_id")
//    private Movie movie;

	
	@ManyToOne
	@JoinColumn(name="movieId")
    private Movie movie;
    }