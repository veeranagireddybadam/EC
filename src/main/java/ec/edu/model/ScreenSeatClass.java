package ec.edu.model;



import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="ScreenSeatClass")
public class ScreenSeatClass {
	

		
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private int screen_seat_type_id;
	
		private String seat_class;
		
		private int total_seat_count;
	
		private double price_per_seat;
		
		@OneToMany(mappedBy="screenseatclass")
		private List<SeatSetup> seatsetup;
		
		
		@ManyToOne
		@JoinColumn(name="screenId")
		private Screens screens;

	

}
