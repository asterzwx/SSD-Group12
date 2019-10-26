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
import hello.model.API_OpponentMain;
import hello.model.API_Serie;
import hello.model.API_Tournament;
import hello.model.League;
import hello.model.LeagueAPI;
import hello.model.Opponent;
import hello.model.Serie;
import hello.model.Tournament;
import hello.repo.DotaLeagueRepo;
import hello.repo.OpponentRepo;
import hello.repo.SerieRepo;
import hello.repo.TournamentRepo;
import hello.service.LeagueService;
import hello.service.OpponentService;
import hello.service.SerieService;
import hello.service.TournamentService;

//@CrossOrigin(origins = "https://gambit-team12.tk")
@RestController
@RequestMapping(value = "/rest/opponent")
public class OpponentController {

	@Autowired
	OpponentRepo opponentRepo;
	@Autowired
	private OpponentService opponentAPIService;
	@Autowired
	private OpponentService  opponentService;

	@GetMapping(path = "dota/opponents/{match_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<API_OpponentMain> getDotaMatchOpponents(@Valid @PathVariable int match_id) throws IOException {
		return opponentAPIService.getDotaMatchOpponentsById(match_id);
	}
	
	@GetMapping(path = "lol/opponents/past/{match_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<API_OpponentMain> getLoLPastMatchOpponents(@Valid @PathVariable int match_id) throws IOException {
		return opponentAPIService.getLoLPastMatchOpponentsById(match_id);
	}
	
	@GetMapping(path = "lol/opponents/running/{match_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<API_OpponentMain> getLoLRunningMatchOpponents(@Valid @PathVariable int match_id) throws IOException {
		return opponentAPIService.getLoLRunningMatchOpponentsById(match_id);
	}
	
	@GetMapping(path = "lol/opponents/upcoming/{match_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<API_OpponentMain> getLoLUpcomingMatchOpponents(@Valid @PathVariable int match_id) throws IOException {
		return opponentAPIService.getLoLUpcomingMatchOpponentsById(match_id);
	}
	
	@GetMapping(path = "dota/opponents/tournament/{tournament_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<API_OpponentMain> getDotaTournamentOpponents(@Valid @PathVariable int tournament_id) throws IOException {
		return opponentAPIService.getDotaTournamentOpponentsById(tournament_id);
	}
	
	
	

//	    @PostMapping("/repos")
//	    public Repository createRepo(@RequestBody Repository newRepo) throws IOException {
//	        return githubService.createRepository(newRepo);
//	    }

	@PostMapping("/create") // Map ONLY POST Requests
	public ResponseEntity create(@Valid @RequestBody Opponent opponent) {
//			if (!leagueService.findById(leagueService.getPoll_id()).isPresent()) {			
		return ResponseEntity.ok(opponentService.saveOpponent(opponent));
//			}
//			return null;
	}

}
