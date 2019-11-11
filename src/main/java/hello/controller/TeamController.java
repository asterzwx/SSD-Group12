package hello.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hello.model.API_Player;
import hello.model.API_Team;
import hello.model.Player;
import hello.model.Team;
import hello.repo.PlayerRepo;
import hello.repo.TeamRepo;
import hello.service.PlayerService;
import hello.service.TeamService;

@CrossOrigin(origins = {"https://gambit-team12.tk", "http://localhost:4200", "https://www.gambit-team12.tk"})
@RestController
@RequestMapping(value = "/rest/team")
public class TeamController {
	
	@Autowired
	TeamRepo teamRepo;
	
	@Autowired
	TeamService teamService;
	
	@Autowired
	TeamService teamAPIService;
	
	@GetMapping(path = "/dota", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<API_Team> getDotaTeams() throws IOException {
		return teamAPIService.getDotaTeams();
	}
	
	@GetMapping(path = "/lol", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<API_Team> getLoLTeams() throws IOException {
		return teamAPIService.getLoLTeams();
	}
	
	// ROUTES FOR RETRIEVING FROM DB
	
	@GetMapping(value = "/db/dota")
	public List<Team> getDBDotaTeams() {
		return teamRepo.findTeamsByVideogame("Dota 2");	
		
	}
	
	@GetMapping(value = "/db/lol")
	public List<Team> getDBLoLTeams() {
		return teamRepo.findTeamsByVideogame("LoL");	
		
	}

}
