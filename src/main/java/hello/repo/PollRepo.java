package hello.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.model.Poll;

public interface PollRepo extends JpaRepository<Poll, Integer>{

	
}
