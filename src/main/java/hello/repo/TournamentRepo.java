package hello.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hello.model.Tournament;

// repo is to save stuff to DB
public interface TournamentRepo extends JpaRepository<Tournament, Integer>{
	
	@Query("SELECT t FROM Tournament t WHERE t.videogame = :videogame")
	List<Tournament> findTournaments(@Param("videogame") String videogame);
	
	@Query("SELECT t FROM Tournament t WHERE cast(t.begin_at as date) < CURDATE()")
	List<Tournament> findAllPastTournaments();
	
	@Query("SELECT t FROM Tournament t WHERE cast(t.begin_at as date) = CURDATE()")
	List<Tournament> findAllRunningTournaments();
	
	@Query("SELECT t FROM Tournament t WHERE cast(t.begin_at as date) > CURDATE()")
	List<Tournament> findAllUpcomingTournaments();
	
	@Query("SELECT t FROM Tournament t WHERE t.videogame = :videogame"
			+ " AND cast(t.begin_at as date) < CURDATE()")
	List<Tournament> findPastTournaments(@Param("videogame") String videogame);
	
	@Query("SELECT t FROM Tournament t WHERE t.videogame = :videogame"
			+ " AND cast(t.begin_at as date) = CURDATE()")
	List<Tournament> findRunningTournaments(@Param("videogame") String videogame);
	
	@Query("SELECT t FROM Tournament t WHERE t.videogame = :videogame"
			+ " AND cast(t.begin_at as date) > CURDATE()")
	List<Tournament> findUpcomingTournaments(@Param("videogame") String videogame);


}
