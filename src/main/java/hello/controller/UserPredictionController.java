package hello.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hello.model.UserPrediction;
import hello.repo.UserPredictionRepo;
import hello.model.UserPrediction;
import hello.service.PollService;
import hello.service.UserPredictionService;

@CrossOrigin(origins = {"https://gambit-team12.tk", "http://localhost:4200"})
@RestController
@RequestMapping(value = "/rest/userprediction")
public class UserPredictionController {
	
	@Autowired
	private UserPredictionService userPredictionService;
	@Autowired
	private UserPredictionRepo userPredictionRepo;

	@GetMapping(value = "/all")
	public List<UserPrediction> getAllUserPredictions() {
		return userPredictionService.getAll();
	}

//	@GetMapping("/{id}")
//	public ResponseEntity<UserPrediction> findById(@PathVariable int id) {
//		Optional<UserPrediction> poll = userPredictionService.findById(id);
//		if (!poll.isPresent()) {
//			ResponseEntity.badRequest().build();
//		}
//		return ResponseEntity.ok(poll.get());
//	}
	
	@GetMapping("/{username}")
	public List<UserPrediction> findByUsername(@Valid @PathVariable String username) {
		return userPredictionRepo.getPredictionsByUsername(username);		
		
	}

	
	

//	@PostMapping("/create") // Map ONLY POST Requests
//	public ResponseEntity create(@RequestBody UserPrediction userPrediction) {
////		if (!userPredictionService.findById(userPrediction.getId()).isPresent()) {			
//			return ResponseEntity.ok(userPredictionService.saveUserPrediction(userPrediction));
////		}
////		return null;	
//	}
	
	
	@PostMapping("/create") // Map ONLY POST Requests
	public Map<String, Object> create(@RequestBody UserPrediction userPrediction) {
		Map<String, Object> json = new HashMap();
		boolean x = true;
		for(UserPrediction u: userPredictionRepo.getPredictionsByUsername(userPrediction.getUsername())) {
			if(u.getMatch_id() == userPrediction.getMatch_id()&& x) {
				json.put("created", "false");	
				x = false;
			}
			else if(x) {
				userPredictionService.saveUserPrediction(userPrediction);
				json.put("created", "true");
			}			
		}
		return json;
	}
	
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<UserPrediction> update(@Valid @PathVariable int id, @RequestBody UserPrediction userPrediction) {
		// here must set because person using this api wouldnt know whats the poll_id 
		// so the requestbody wont include the poll_id, so we must set for them
		userPrediction.setId(id);
		if (!userPredictionService.findById(id).isPresent()) {
//			ResponseEntity.badRequest().build();
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(userPredictionService.saveUserPrediction(userPrediction));
	}
	
	@DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        if (!userPredictionService.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        userPredictionService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
