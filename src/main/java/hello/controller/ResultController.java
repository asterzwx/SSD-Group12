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
import hello.model.Result;
import hello.repo.ResultRepo;
import hello.service.PollService;
import hello.service.ResultService;

@CrossOrigin(origins = {"https://gambit-team12.tk", "http://localhost:4200"})
@RestController
@RequestMapping(value = "/rest/result")
public class ResultController {
	@Autowired
	private ResultService resultService;
	@Autowired
	private ResultRepo resultRepo;

	@GetMapping(value = "/all")
	public List<Result> getAllResult() {
		return resultService.getAll();
	}
	
}
