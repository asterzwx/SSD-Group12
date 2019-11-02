package hello.controller;

import java.util.List;
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


import hello.model.Poll;
import hello.service.PollService;

@CrossOrigin(origins = {"https://gambit-team12.tk", "http://localhost:4200"})
@RestController
@RequestMapping(value = "/rest/poll")
public class PollController {
	@Autowired
	private PollService pollService;

	@GetMapping(value = "/all")
	public List<Poll> getAllPoll() {
		return pollService.getAll();
	}
	
	@GetMapping("/{poll_id}")
	public ResponseEntity<Poll> findById(@PathVariable int poll_id) {
		Optional<Poll> poll = pollService.findById(poll_id);
		if (!poll.isPresent()) {
			ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(poll.get());
	}

	@PostMapping("/create") // Map ONLY POST Requests
	public ResponseEntity create(@RequestBody Poll poll) {
		if (!pollService.findById(poll.getPoll_id()).isPresent()) {			
			return ResponseEntity.ok(pollService.savePoll(poll));
		}
		return null;
	}
	
	@PutMapping("/update/{poll_id}")
	public ResponseEntity<Poll> update(@Valid @PathVariable int poll_id, @RequestBody Poll poll) {
		// here must set because person using this api wouldnt know whats the poll_id 
		// so the requestbody wont include the poll_id, so we must set for them
		poll.setPoll_id(poll_id);
		if (!pollService.findById(poll_id).isPresent()) {
//			ResponseEntity.badRequest().build();
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(pollService.savePoll(poll));
	}
	
	@DeleteMapping("/delete/{poll_id}")
    public ResponseEntity delete(@PathVariable int poll_id) {
        if (!pollService.findById(poll_id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        pollService.deleteById(poll_id);

        return ResponseEntity.ok().build();
    }

}
