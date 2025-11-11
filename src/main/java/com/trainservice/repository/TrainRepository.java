package com.trainservice.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.trainservice.entity.Train;

@Repository
public interface TrainRepository extends JpaRepository<Train,String> {

	List<Train> findBySourceAndDestination(String source, String destination);

	
}
