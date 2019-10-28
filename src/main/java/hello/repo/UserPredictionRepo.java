package hello.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hello.model.Opponent;
import hello.model.UserPrediction;

public interface UserPredictionRepo extends JpaRepository<UserPrediction, Integer>{
	
	

}
