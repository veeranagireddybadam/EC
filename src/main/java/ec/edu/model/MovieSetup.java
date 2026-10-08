package ec.edu.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
public class MovieSetup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieSetupId;

    private String showtime;

    @Temporal(TemporalType.DATE)
    private Date showDate;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "screen_id")
    private Screens screen;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
