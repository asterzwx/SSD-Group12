package hello;

import java.util.List;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.hibernate.type.NTextType;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import hello.model.API_Game;
import hello.model.API_League;
import hello.model.API_Match;
import hello.model.API_Player;
import hello.model.API_Serie;
import hello.model.API_Team;
import hello.model.API_Tournament;
import hello.model.LeagueAPI;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RepositoryInterface {

	// PANDASCORE API ENDPOINTS
	@GET("dota2/series")
	Call<List<API_Serie>> listDotaSeries(@Query("token") String accessToken);
	
	@GET("lol/series")
	Call<List<API_Serie>> listLoLSeries(@Query("token") String accessToken);

	// league has both dota and lol data
	@GET("leagues")
	Call<List<API_League>> listLeagues(@Query("token") String accessToken);

	@GET("tournaments")
	Call<List<API_Tournament>> listTournaments(@Query("token") String accessToken);

	@GET("dota2/tournaments")
	Call<List<API_Tournament>> listDotaTournaments(@Query("token") String accessToken);
	
	@GET("lol/tournaments/past")
	Call<List<API_Tournament>> listLoLPastTournaments(@Query("token") String accessToken);
	
	@GET("lol/tournaments/running")
	Call<List<API_Tournament>> listLoLRunningTournaments(@Query("token") String accessToken);
	
	@GET("lol/tournaments/upcoming")
	Call<List<API_Tournament>> listLoLUpcomingTournaments(@Query("token") String accessToken);

	
	
	
	@GET("matches/past")
	Call<List<API_Match>> listAllPastMatches(@Query("token") String accessToken,
			@Query("per_page") int per_page,
			@Query("page") int page);
	
	@GET("matches/running")
	Call<List<API_Match>> listAllRunningMatches(@Query("token") String accessToken,
			@Query("per_page") int per_page,
			@Query("page") int page);
	
	@GET("matches/upcoming")
	Call<List<API_Match>> listAllUpcomingMatches(@Query("token") String accessToken,
			@Query("per_page") int per_page,
			@Query("page") int page);
	
	
	
	@GET("lol/matches")
	Call<List<API_Match>> listAllLoLMatches(@Query("token") String accessToken, 
			@Query("per_page") int per_page, @Query("range[begin_at]") String date,
			@Query("sort") String sort);
	
	@GET("dota2/matches")
	Call<List<API_Match>> listAllDotaMatches(@Query("token") String accessToken, 
			@Query("per_page") int per_page, @Query("range[begin_at]") String date,
			@Query("sort") String sort);
	
	
	

	@GET("lol/matches/past")
	Call<List<API_Match>> listLoLPastMatches(@Query("token") String accessToken);

	@GET("lol/matches/running")
	Call<List<API_Match>> listLoLRunningMatches(@Query("token") String accessToken);

	@GET("lol/matches/upcoming")
	Call<List<API_Match>> listLoLUpcomingMatches(@Query("token") String accessToken);

//	@GET("matches/{game_id}")
//	Call<List<API_Game>> listGames(@Query("token") String accessToken);
//
	@GET("dota2/matches")
	Call<List<API_Match>> listDotaGamesByMatchId(@Query("token") String accessToken);

	@GET("lol/matches")
	Call<List<API_Match>> listLoLGamesByMatchId(@Query("token") String accessToken);
	
	
	@GET("dota2/series/1821/players")
	Call<List<API_Player>> listDotaPlayers(@Query("token") String accessToken,
			@Query("per_page") int per_page,
			@Query("page") int page);

	@GET("lol/series/1839/players")
	Call<List<API_Player>> listLoLPlayers(@Query("token") String accessToken,
			@Query("per_page") int per_page,
			@Query("page") int page);


	@GET("dota2/series/1821/teams")
	Call<List<API_Team>> listDotaTeams(@Query("token") String accessToken,
			@Query("per_page") int per_page,
			@Query("page") int page);
	
	@GET("lol/series/1839/teams")
	Call<List<API_Team>> listLoLTeams(@Query("token") String accessToken,
			@Query("per_page") int per_page,
			@Query("page") int page);
}
