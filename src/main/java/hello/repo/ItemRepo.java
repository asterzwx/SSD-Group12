package hello.repo;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hello.model.Item;

public interface ItemRepo extends JpaRepository<Item, Integer>{
	
//	Optional<Item> findById(int item_id);

//	@Query("SELECT m.match_name as match_name, m.match_id as match_id, m.begin_at as begin_at,  "
//			+ "m.num_of_games as num_of_games, r.score as score, r.team_id as team_id, o.acronym as acronym, " + 
//			" o.image_url as image_url FROM Match m, Result r, Opponent o "
//			+ "WHERE m.match_id = r.match_id AND o.id = r.team_id AND m.match_id = :match_id")
//	List<Map<String, String>> findItemsByUsername(int match_id);

}
