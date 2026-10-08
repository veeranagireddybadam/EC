package ec.edu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="CrewAndCast")
public class CrewAndCast {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int member_id;

    @Column(name="name")
    private String name;

    @Column(name="role")
    private String role;

    @Column(name="member_type")
    private String member_type;

    @Column(name="department")
    private String department;

    @Column(name="photo_url")
    private String photo_url;

    @ManyToOne
    @JoinColumn(name="movie_id")
    private Movie movie;
}
