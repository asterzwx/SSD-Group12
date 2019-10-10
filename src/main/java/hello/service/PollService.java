package hello.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.model.Poll;
import hello.repo.PollRepo;

@Service
@Transactional
public class PollService {
	@Autowired
	PollRepo pollRepo;
	
	public List<Poll> getAll() {
		// TODO Auto-generated method stub				
		return pollRepo.findAll();
	}
	
	public Poll savePoll(Poll poll) {		
		return pollRepo.save(poll);
	}
	
	public Optional<Poll> findById(int poll_id) {
		// TODO Auto-generated method stub
        return pollRepo.findById(poll_id);        
	}
	
	public void deleteById(int poll_id) {
		pollRepo.deleteById(poll_id);
    }


}
