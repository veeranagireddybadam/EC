package ec.edu.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class SeatSetup {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
     private int seatSetupId;
	
     private String rowLabel;
     
     private int seat_PerRow;
     
    
     
     
     @ManyToOne(fetch = FetchType.EAGER)
     @JoinColumn(name="screen_seat_type_id")
     @JsonIgnore
     private ScreenSeatClass screenseatclass;
     
     
     @ManyToOne
     @JoinColumn(name="screenId")
     private Screens screens;
     
     

     @OneToMany(mappedBy="seatsetup")
     @JsonIgnore
    private List <SeatNumberAllocation> seatnumberallocation;


}
