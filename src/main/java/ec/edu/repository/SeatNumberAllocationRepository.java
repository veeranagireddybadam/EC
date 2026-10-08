package ec.edu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.edu.model.SeatNumberAllocation;
import ec.edu.model.SeatSetup;

@Repository
public interface SeatNumberAllocationRepository extends JpaRepository<SeatNumberAllocation,Integer> {

	List<SeatNumberAllocation> findBySeatsetup(SeatSetup setup);

	//List<SeatNumberAllocation> findBySeatSetup(SeatSetup setup);

}
