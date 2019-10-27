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

import hello.model.API_League;
import hello.model.API_Tournament;
import hello.model.League;
import hello.model.LeagueAPI;
import hello.model.Tournament;
import hello.repo.DotaLeagueRepo;
import hello.repo.TournamentRepo;
import hello.service.LeagueService;
import hello.service.TournamentService;

//@CrossOrigin(origins = "https://gambit-team12.tk")
@RestController
@RequestMapping(value = "/rest/tournament")
public class TournamentController {

	@Autowired
	TournamentRepo tournamentRepo;
	@Autowired
	private TournamentService tournamentAPIService;
	@Autowired
	private TournamentService tournamentService;

	@GetMapping(path = "/dota", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<API_Tournament> getDotaTournaments() throws IOException {
		return tournamentAPIService.getDotaTournaments();
	}
	
	@GetMapping(path = "/lol/past", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<API_Tournament> getLoLPastTournaments() throws IOException {
		return tournamentAPIService.getLoLPastTournaments();
	}
	
	@GetMapping(path = "/lol/running", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<API_Tournament> getLoLRunningTournaments() throws IOException {
		return tournamentAPIService.getLoLRunningTournaments();
	}
	
	@GetMapping(path = "/lol/upcoming", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<API_Tournament> getLoLUpcomingTournaments() throws IOException {
		return tournamentAPIService.getLoLUpcomingTournaments();
	}

//	    @PostMapping("/repos")
//	    public Repository createRepo(@RequestBody Repository newRepo) throws IOException {
//	        return githubService.createRepository(newRepo);
//	    }

	@PostMapping("/create") // Map ONLY POST Requests
	public ResponseEntity create(@Valid @RequestBody Tournament tournament) {
//			if (!leagueService.findById(leagueService.getPoll_id()).isPresent()) {			
		return ResponseEntity.ok(tournamentService.saveTournament(tournament));
//			}
//			return null;
	}

}
