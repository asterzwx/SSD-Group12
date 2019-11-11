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
import hello.repo.LeagueRepo;
import hello.repo.GameRepo;
import hello.repo.MatchRepo;
import hello.service.GameService;
import hello.service.LeagueService;
import hello.service.MatchService;

@CrossOrigin(origins = {"https://gambit-team12.tk", "http://localhost:4200", "https://www.gambit-team12.tk"})
@RestController
@RequestMapping(value = "/rest/game")
public class GameController {

	@Autowired
	GameRepo gameRepo;

	@Autowired
	private GameService gameAPIService;
	@Autowired
	private GameService gameService;
	@Autowired
	private MatchService matchAPIService;

	// ROUTES FOR RETRIEVING FROM DB

	@GetMapping(value = "/all")
	public List<Game> getAllGames() {
		return gameService.getAll();
	}

	@GetMapping(value = "/db/dota/{match_id}")
	public List<Game> getDotaGamesByMatchId(@Valid @PathVariable int match_id) {
		return gameRepo.findGamesByMatchId("Dota 2", match_id);
	}

	@GetMapping(value = "/db/lol/{match_id}")
	public List<Game> getLoLGamesByMatchId(@Valid @PathVariable int match_id) {
		return gameRepo.findGamesByMatchId("LoL", match_id);
	}

	@GetMapping(value = "/{game_id}")
	public List<Game> getGameById(@Valid @PathVariable int game_id) {
		return gameRepo.findGameById(game_id);
	}
	
}
