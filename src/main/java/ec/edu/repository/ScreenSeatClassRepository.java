package ec.edu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.edu.model.ScreenSeatClass;
import ec.edu.model.Screens;

@Repository
public interface ScreenSeatClassRepository extends JpaRepository <ScreenSeatClass , Integer> {

	List<ScreenSeatClass> findByScreensScreenId(int screenId);

	List<ScreenSeatClass> findByScreens(Screens screen);

}
