package hello.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hello.model.API_League;
import hello.model.API_Player;
import hello.model.Match;
import hello.model.Opponent;
import hello.model.Player;
import hello.repo.PlayerRepo;
import hello.service.PlayerService;

//@CrossOrigin(origins = "https://gambit-team12.tk")
@RestController
@RequestMapping(value = "/rest/player")
public class PlayerController {
	
	@Autowired
	PlayerRepo playerRepo;
	
	@Autowired
	PlayerService playerService;
	
	@Autowired
	PlayerService playerAPIService;
	
	@GetMapping(path = "/dota", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<API_Player> getDotaPlayers() throws IOException {
		return playerAPIService.getDotaPlayers();
	}
	
	@GetMapping(path = "/lol", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<API_Player> getLoLPlayers() throws IOException {
		return playerAPIService.getLoLPlayers();
	}
	
	// ROUTES FOR RETRIEVING FROM DB
	
	@GetMapping(value = "/db/dota")
	public List<Player> getDBDotaPlayers() {
		return playerRepo.findPlayersByVideogame("Dota 2");	
		
	}
	
	@GetMapping(value = "/db/lol")
	public List<Player> getDBLoLPlayers() {
		return playerRepo.findPlayersByVideogame("LoL");	
		
	}

}
