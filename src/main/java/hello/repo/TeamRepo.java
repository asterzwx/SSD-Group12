package hello.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import hello.model.Team;

public interface TeamRepo extends JpaRepository<Team, Integer>{
	
	@Query("SELECT t FROM Team t WHERE t.videogame = :videogame")
	List<Team> findTeamsByVideogame(@Param("videogame") String videogame);

}
