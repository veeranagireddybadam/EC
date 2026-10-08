package ec.edu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.edu.model.ScreenSeatClass;
import ec.edu.model.SeatSetup;

public interface SeatSetupRepository  extends JpaRepository<SeatSetup,Integer>{

	List<SeatSetup> findByScreenseatclass(ScreenSeatClass seatClass);

	//List<SeatSetup> findByScreenSeatClass(ScreenSeatClass seatClass);

}
