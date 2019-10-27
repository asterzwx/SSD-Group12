package hello.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import hello.model.League;


// repo is to save stuff to DB
public interface LeagueRepo extends JpaRepository<League, Integer>{
	
	@Query("SELECT l FROM League l WHERE l.videogame = :videogame")
	List<League> findLeagues(@Param("videogame") String videogame);

}
