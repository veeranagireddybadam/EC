package ec.edu.model;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ec.edu.dto.ScreenSeatClassDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Screens {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int screenId;
	
	@Column(name="screenName")
    private String screenName;
    
    @Column(name="seatingCapacity")
    private int seatingCapacity;
    
//    private String rowLable;
//    
    
    
 
    @ManyToOne
	@JoinColumn(name="userId")
	private User user;
    

    @OneToMany(mappedBy="screens")
    @JsonIgnore
    private List<SeatSetup> seatsetup;
    
    
    
    
	 @OneToMany(mappedBy = "screens")
	 @JsonIgnore
    private List<ScreenSeatClass> screenseatclass;
	 
	 
    
    
}
