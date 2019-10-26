package hello.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import hello.model.API_Game;
import hello.model.API_League;
import hello.model.API_Match;
import hello.model.Game;
import hello.model.League;
import hello.model.LeagueAPI;
import hello.model.Match;
import hello.repo.DotaLeagueRepo;
import hello.repo.GameRepo;
import hello.repo.MatchRepo;
import hello.service.GameService;
import hello.service.LeagueService;
import hello.service.MatchService;

//@CrossOrigin(origins = "https://gambit-team12.tk")
@RestController
@RequestMapping(value = "/rest")
public class GameController {

	@Autowired
	GameRepo gameRepo;

	@Autowired
	private GameService gameAPIService;
	@Autowired
	private GameService gameService;
	@Autowired
	private MatchService matchAPIService;
	
//	@GetMapping(path = "dota/games/{match_id}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public List<API_Game> getDotaGamesById(@Valid @PathVariable int match_id) throws IOException {
//		return matchAPIService.getDotaGamesById(match_id);
//	}

//	@GetMapping(path = "games/{match_id}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public List<API_Game> getGamesByMatchId(int match_id) throws IOException {
//		return gameAPIService.getDotaGamesByMatchId(match_id);
//	}
	
//	    @PostMapping("/repos")
//	    public Repository createRepo(@RequestBody Repository newRepo) throws IOException {
//	        return githubService.createRepository(newRepo);
//	    }

	
}
