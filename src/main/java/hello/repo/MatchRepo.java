package hello.repo;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hello.model.Match;
import hello.model.Tournament;

//
public interface MatchRepo extends JpaRepository<Match, Integer> {

	@Query("SELECT m FROM Match m WHERE m.videogame = :videogame")
	List<Match> findMatches(@Param("videogame") String videogame);

	@Query("SELECT m FROM Match m WHERE cast(m.begin_at as date) < CURDATE()")
	List<Match> findLoLPastMatches();

	@Query("SELECT m FROM Match m WHERE cast(m.begin_at as date) = CURDATE()")
	List<Match> findLoLRunningMatches();

	@Query("SELECT m FROM Match m WHERE cast(m.begin_at as date) > CURDATE() OR m.begin_at = ''")
	List<Match> findLoLUpcomingMatches();

	@Query("SELECT m FROM Match m WHERE m.tournament_id = :tournament_id")
	List<Match> findMatchesByTournamentId(@Param("tournament_id") int tournament_id);

	@Query("SELECT m.match_name as match_name, m.match_id as match_id, m.begin_at as begin_at,  "
			+ "m.num_of_games as num_of_games, r.score as score, r.team_id as team_id, o.acronym as acronym, " + 
			" o.image_url as image_url FROM Match m, Result r, Opponent o "
			+ "WHERE m.match_id = r.match_id AND o.id = r.team_id AND m.match_id = :match_id")
//	List<MatchView> findResultsByMatchId(int match_id);
	List<Map<String, String>> findResultsByMatchId(int match_id);
}
