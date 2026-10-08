package ec.edu.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int userId;

    private String userName;
    @Column( name="email",unique = true)
    private String email;
    private String phone;
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Movie> movies = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        Date now = new Date();
        createdAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }
    
    @OneToMany(mappedBy = "user",cascade =CascadeType.ALL )
    @JsonIgnore
	private List<MovieBite> movieByte;
    
    @OneToMany(mappedBy = "user",cascade =CascadeType.ALL)
    @JsonIgnore
    private List<Screens> screens;
    
    @OneToMany(mappedBy = "user",cascade =CascadeType.ALL)
    @JsonIgnore
    private List<BookingTicket> bookingTicket;
    
    
}
