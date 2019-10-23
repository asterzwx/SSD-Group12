package hello.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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

import hello.model.League;
import hello.service.GithubService;
import hello.service.LeagueService;

//@CrossOrigin(origins = "https://gambit-team12.tk")
@RestController
//@RequestMapping(value = "/rest/league")
public class LeagueController {

	 @Autowired
	    private LeagueService githubService;

	    @GetMapping("/leagues")
	    public List<League> getRepos() throws IOException {
	        return githubService.getRepositories();
	    }

//	    @PostMapping("/repos")
//	    public Repository createRepo(@RequestBody Repository newRepo) throws IOException {
//	        return githubService.createRepository(newRepo);
//	    }

	  

}
