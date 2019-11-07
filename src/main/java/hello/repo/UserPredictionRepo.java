package hello.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hello.model.Opponent;
import hello.model.UserPrediction;

public interface UserPredictionRepo extends JpaRepository<UserPrediction, Integer>{
	
	@Modifying
	@Query("UPDATE UserPrediction p SET p.result = :result WHERE p.match_id = :match_id")
	int updatePredictionResult(@Param("result") int result,
			@Param("match_id") int match_id);
	
	@Modifying
	@Query("UPDATE UserPrediction p SET p.points_allocated = 1 WHERE p.match_id = :match_id")
	int updatePointsAllocatedForMatch(@Param("match_id") int match_id);
	
	@Query("SELECT p FROM UserPrediction p WHERE p.match_id = :match_id")
	List<UserPrediction> getUsernamesByMatchId(@Param("match_id") int match_id);
	
	@Query("SELECT p FROM UserPrediction p WHERE p.username = :username")
	List<UserPrediction> getPredictionsByUsername(@Param("username") String username);
	
	
}
