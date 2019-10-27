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
import hello.model.League;
import hello.model.LeagueAPI;
import hello.model.Match;
import hello.repo.DotaLeagueRepo;
import hello.repo.MatchRepo;
import hello.service.GameService;
import hello.service.LeagueService;
import hello.service.MatchService;

//@CrossOrigin(origins = "https://gambit-team12.tk")
@RestController
@RequestMapping(value = "/rest/match")
public class MatchController {

	@Autowired
	MatchRepo matchRepo;
	@Autowired
	private MatchService matchAPIService;
	@Autowired
	private MatchService matchService;
	@Autowired
	private GameService gameAPIService;

	@GetMapping(path = "dota/past", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<API_Match> getDotaMatches() throws IOException {
		return matchAPIService.getDotaMatches();
	}
	
	@GetMapping(path = "lol/past", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<API_Match> getLoLPastMatches() throws IOException {
		return matchAPIService.getLoLPastMatches();
	}
	@GetMapping(path = "lol/running", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<API_Match> getLoLRunningMatches() throws IOException {
		return matchAPIService.getLoLRunningMatches();
	}
	@GetMapping(path = "lol/upcoming", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<API_Match> getLoLUpcomingMatches() throws IOException {
		return matchAPIService.getLoLUpcomingMatches();
	}
	
	@GetMapping(path = "dota/games/{match_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<API_Game> getDotaGamesById(@Valid @PathVariable int match_id) throws IOException {
		return matchAPIService.getDotaGamesById(match_id);
	}
	
	@GetMapping(path = "lol/games/{match_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<API_Game> getLoLGamesById(@Valid @PathVariable int match_id) throws IOException {
		return matchAPIService.getLoLGamesById(match_id);
	}

//	    @PostMapping("/repos")
//	    public Repository createRepo(@RequestBody Repository newRepo) throws IOException {
//	        return githubService.createRepository(newRepo);
//	    }

	@PostMapping("/create") // Map ONLY POST Requests
	public ResponseEntity create(@Valid @RequestBody Match match) {
//			if (!leagueService.findById(leagueService.getPoll_id()).isPresent()) {			
		return ResponseEntity.ok(matchService.saveMatch(match));
//			}
//			return null;
	}

}
