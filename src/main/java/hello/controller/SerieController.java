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
import hello.model.API_Serie;
import hello.model.API_Tournament;
import hello.model.Item;
import hello.model.League;
import hello.model.LeagueAPI;
import hello.model.Serie;
import hello.model.Tournament;
import hello.repo.LeagueRepo;
import hello.repo.SerieRepo;
import hello.repo.TournamentRepo;
import hello.service.LeagueService;
import hello.service.SerieService;
import hello.service.TournamentService;

//@CrossOrigin(origins = "https://gambit-team12.tk")
@RestController
@RequestMapping(value = "/rest/serie")
public class SerieController {

	@Autowired
	SerieRepo serieRepo;
	@Autowired
	private SerieService serieAPIService;
	@Autowired
	private SerieService serieService;

	@GetMapping(path = "/dota", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<API_Serie> getDotaSeries() throws IOException {
		return serieAPIService.getDotaSeries();
	}

	@GetMapping(path = "/lol", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<API_Serie> getLoLSeries() throws IOException {
		return serieAPIService.getLoLSeries();
	}

	@PostMapping("/create") // Map ONLY POST Requests
	public ResponseEntity create(@Valid @RequestBody Serie serie) {
//			if (!leagueService.findById(leagueService.getPoll_id()).isPresent()) {			
		return ResponseEntity.ok(serieService.saveSerie(serie));
//			}
//			return null;
	}

	// ROUTES FOR RETRIEVING FROM DB

	@GetMapping(value = "/all")
	public List<Serie> getAllSerie() {
		return serieService.getAll();
	}

	@GetMapping(value = "/db/dota")
	public List<Serie> getAllDotaSerieFromDB() {
		return serieRepo.findSeries("Dota 2");
	}

	@GetMapping(value = "/db/lol")
	public List<Serie> getAllLoLSerieFromDB() {
		return serieRepo.findSeries("LoL");
	}

}
