package hello.service;

import java.io.IOException;
import java.util.List;

import javax.validation.constraints.Null;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import hello.APIConfiguration;
import hello.RepositoryInterface;
import hello.model.API_Match;
import hello.model.API_Game;
import hello.model.API_League;
import hello.model.API_Match;
import hello.model.API_Match;
import hello.model.API_Tournament;
import hello.model.Game;
import hello.model.League;
import hello.model.Match;
import hello.model.Tournament;
import hello.repo.DotaLeagueRepo;
import hello.repo.GameRepo;
import hello.repo.MatchRepo;
import hello.repo.TournamentRepo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Service
public class GameService implements APIConfiguration {
	@Autowired
	GameRepo gameRepo;
	private RepositoryInterface service;
	List<API_Game> games = null;
	List<API_Match> matches = null;

	public GameService() {
		Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.pandascore.co/")
				.addConverterFactory(GsonConverterFactory.create()).build();
		service = retrofit.create(RepositoryInterface.class);
	}	
	

//	public List<API_Game> getDotaGamesByMatchId(int match_id) throws IOException {
//		Call<List<API_Match>> call = service.listDotaGamesByMatchId(API_KEY);
//		call.enqueue(new Callback<List<API_Match>>() {
//			@Override
//			public void onResponse(Call<List<API_Match>> call, Response<List<API_Match>> response) {
//
//				matches = response.body();
//				Gson responseGson = new Gson();								
//				
//				for (API_Match u : response.body()) {					
//					if(match_id == u.getId()) {
////						Game game = new Game();
//						GameService gameService = new GameService();
//						for(API_Game g : u.getGames()) {
//							gameService.
//							saveGameDetails(g.getId(), 
//									g.getBeginAt(),
//									g.getEndAt(),
//									u.getId(),
//									g.getPosition(),
//									g.getStatus(),
//									g.getWinner().getId().toString(),
//									g.getVideogame().getName());							
//						}						
//					}					
//				}
//				System.out.println("Saved Dota games for " + match_id + " to DB");
//				
//
//			}
//
//			@Override
//			public void onFailure(Call<List<API_Match>> call, Throwable t) {
//				// TODO Auto-generated method stub
//				System.out.println("ERROR: " + t.getMessage());
//			}
//		});
//		return games;
//	}

	public List<Game> getAll() {
		// TODO Auto-generated method stub
		return gameRepo.findAll();
	}

//	public Game saveGame(Game game) {
//		return gameRepo.save(game);
//	}
//
//	public Game saveGameDetails(int game_id, String begin_at, String end_at, int match_id,
//			int position, String status, String winner_id, String videogame) {
//		Game game = new Game();
//		game.setGame_id(game_id);
//		game.setBegin_at(begin_at);
//		game.setEnd_at(end_at);
//		game.setMatch_id(match_id);
//		game.setPosition(position);
//		game.setStatus(status);
//		game.setWinner_id(winner_id);
//		game.setVideogame(videogame);
//		return gameRepo.save(game);
//	}

}
