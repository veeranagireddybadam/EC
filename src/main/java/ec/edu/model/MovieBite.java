package ec.edu.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

@Entity
@Data
public class MovieBite {
	@Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
	private Integer biteId;
	
	private String  biteName;
	
	private Double bitePrice;
	
	
	private String moviebiteUrl;
	
	private  int createdBy;
	
	private int updatedBy;
	
	private Date createdDate;
	
	private Date updatedDate;
	
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	@OneToMany(mappedBy = "movieBite")
	private List<BookingMovieBites> bookingMovieBites;

	
	
	   @PrePersist
	    protected void onCreate() {
	        Date now = new Date();
	        createdDate = now;
	        updatedDate = now;
	    }
//
//	   @PreUpdate
//	    protected void onUpdate() {
//	        updatedDate = new Date();
//	    }
    
	   @PreUpdate
	   protected void onUpdate() {
		   updatedDate = new Date();
	   }

}