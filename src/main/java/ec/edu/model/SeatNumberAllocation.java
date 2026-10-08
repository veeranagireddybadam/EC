package ec.edu.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class SeatNumberAllocation {
	    
	    @Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
       private int seatNumberAllocationId;
	   
	    @Column(name="seatNumber")
        private String seatNumber;
	    
//	    @Column(name="seatStatus")
//	    private String seatStatus;
        
        @ManyToOne
        @JoinColumn(name="seatSetupId",nullable=false)
        private SeatSetup seatsetup; 
        
        @OneToMany(mappedBy = "seatNumberAllocation")
        @JsonIgnore
        private List<BookingTicketNumber> bookingTicketNumber;
        
       }
