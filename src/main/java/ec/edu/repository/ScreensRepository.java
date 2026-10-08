package ec.edu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.edu.model.ScreenSeatClass;
import ec.edu.model.Screens;


@Repository
public interface ScreensRepository extends JpaRepository<Screens,Integer> {

	Optional<Screens> findByScreenName(String screenName);

	//void saveAllAndFlush(List<ScreenSeatClass> existingSeatClasses);

//	List<ScreenSeatClass> findByScreensScreenId(int screenId);

	//void saveAll(List<ScreenSeatClass> existingSeatClasses);


}
