package hello.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hello.model.Poll;
import hello.model.Serie;

public interface PollRepo extends JpaRepository<Poll, Integer>{

}
