package hello.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hello.model.Game;
import hello.model.Match;
import hello.model.Opponent;
import hello.model.Result;
//
public interface ResultRepo extends JpaRepository<Result, Integer>{

//	@Query("SELECT o FROM Opponent o WHERE o.match_id = :match_id")
//	List<Opponent> findOpponentByMatchId(@Param("match_id") int match_id);


}
