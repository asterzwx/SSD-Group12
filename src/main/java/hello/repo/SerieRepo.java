package hello.repo;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hello.model.Match;
import hello.model.Serie;
//
public interface SerieRepo extends JpaRepository<Serie, Integer>{
	
	@Query("SELECT s FROM Serie s WHERE s.videogame = :videogame")
	List<Serie> findDotaSeries(@Param("videogame") String videogame);

}
