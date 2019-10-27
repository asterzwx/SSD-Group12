package hello.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hello.model.Match;
import hello.model.Tournament;
//
public interface MatchRepo extends JpaRepository<Match, Integer>{
	
	@Query("SELECT m FROM Match m WHERE m.videogame = :videogame")
	List<Match> findMatches(@Param("videogame") String videogame);
	
	@Query("SELECT m FROM Match m WHERE cast(m.begin_at as date) < CURDATE()")
	List<Match> findLoLPastMatches();
	
	@Query("SELECT m FROM Match m WHERE cast(m.begin_at as date) = CURDATE()")
	List<Match> findLoLRunningMatches();
	
	@Query("SELECT m FROM Match m WHERE cast(m.begin_at as date) > CURDATE()")
	List<Match> findLoLUpcomingMatches();
	
	@Query("SELECT m FROM Match m WHERE m.tournament_id = :tournament_id")
	List<Match> findMatchesByTournamentId(@Param("tournament_id") int tournament_id);
}
