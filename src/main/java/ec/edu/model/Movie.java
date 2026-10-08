package ec.edu.model;


import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieId;
    @Column(name="movie_title")
    private String movieTitle;
    private String genre;
    private String language;
    private String duration;
    @Column(name="description", length=500)
    private String description;
    private Date releaseDate;
    private String posterUrl;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CrewAndCast> crewAndCasts;


    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    private int createdBy;
    private int updatedBy;
    @PrePersist
    public void prePersist() {
        createdDate = new Date();
        }

    @PreUpdate
    public void preUpdate() {
        updatedDate = new Date();
    }
    
    

   
}
