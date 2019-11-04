package hello.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hello.model.Poll;
import hello.model.Serie;

public interface PollRepo extends JpaRepository<Poll, Integer>{
	
	@Query("SELECT p FROM Poll p WHERE p.match_id = :match_id")
	List<Poll> findPollByMatchId(@Param("match_id") int match_id);


}
