package hello.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hello.model.Game;
import hello.model.Match;
import hello.model.Opponent;
import hello.model.Player;
//
public interface PlayerRepo extends JpaRepository<Player, Integer>{
	
	@Query("SELECT p FROM Player p WHERE p.videogame = :videogame")
	List<Player> findPlayersByVideogame(@Param("videogame") String videogame);

}
