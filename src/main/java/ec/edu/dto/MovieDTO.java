package ec.edu.dto;

import java.util.Date;
import java.util.List;

import ec.edu.model.Role;
import ec.edu.model.Screens;
import ec.edu.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {

    private int movieId;
    private String movieTitle;
    private String genre;
    private String language;
    private String duration;
    private Date releaseDate;
    private String posterUrl;
    private String description;

    private User user;
    
    private Date createdDate;
    
    private Date updatedDate;
    
    private int createdBy;

    private int updatedBy;

}


