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
import hello.model.League;
import hello.model.LeagueAPI;
import hello.model.Serie;
import hello.repo.LeagueRepo;
import hello.service.LeagueService;

@CrossOrigin(origins = {"https://gambit-team12.tk", "http://localhost:4200", "https://www.gambit-team12.tk"})
@RestController
@RequestMapping(value = "/rest/league")
public class LeagueController {

	@Autowired
	LeagueRepo leagueRepo;

	@Autowired
	private LeagueService leagueAPIService;
	@Autowired
	private LeagueService leagueService;

	@GetMapping(path = "/leagues", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<API_League> getLeagues() throws IOException {
		return leagueAPIService.getLeagues();
	}

	@PostMapping("/create") // Map ONLY POST Requests
	public ResponseEntity create(@Valid @RequestBody League league) {
//			if (!leagueService.findById(leagueService.getPoll_id()).isPresent()) {			
		return ResponseEntity.ok(leagueService.saveLeague(league));
//			}
//			return null;
	}

	// ROUTES FOR RETRIEVING FROM DB

	@GetMapping(value = "/all")
	public List<League> getAllLeagues() {
		return leagueService.getAll();
	}

	@GetMapping(value = "/db/dota")
	public List<League> getAllDotaLeaguesFromDB() {
		return leagueRepo.findLeagues("Dota 2");
	}

	@GetMapping(value = "/db/lol")
	public List<League> getAllLoLLeaguesFromDB() {
		return leagueRepo.findLeagues("LoL");
	}

}
