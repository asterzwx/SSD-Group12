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
import hello.model.League;
import hello.model.LeagueAPI;
import hello.model.Serie;
import hello.model.Tournament;
import hello.repo.DotaLeagueRepo;
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
	private SerieService  serieService;

	@GetMapping(path = "/dota/series", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<API_Serie> getDotaSeries() throws IOException {
		return serieAPIService.getDotaSeries();
	}

//	    @PostMapping("/repos")
//	    public Repository createRepo(@RequestBody Repository newRepo) throws IOException {
//	        return githubService.createRepository(newRepo);
//	    }

	@PostMapping("/create") // Map ONLY POST Requests
	public ResponseEntity create(@Valid @RequestBody Serie serie) {
//			if (!leagueService.findById(leagueService.getPoll_id()).isPresent()) {			
		return ResponseEntity.ok(serieService.saveSerie(serie));
//			}
//			return null;
	}

}
