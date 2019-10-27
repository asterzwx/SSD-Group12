package hello.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hello.model.Game;
import hello.model.Serie;
//
public interface GameRepo extends JpaRepository<Game, Integer>{
	
	@Query("SELECT g FROM Game g WHERE g.videogame = :videogame AND g.match_id = :match_id")
	List<Game> findGamesByMatchId(@Param("videogame") String videogame,
			@Param("match_id") int match_id);
	
	@Query("SELECT g FROM Game g WHERE g.game_id = :game_id")
	List<Game> findGameById(@Param("game_id") int game_id);

}
