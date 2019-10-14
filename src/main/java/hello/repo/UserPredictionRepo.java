package hello.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.model.UserPrediction;

public interface UserPredictionRepo extends JpaRepository<UserPrediction, Integer>{

}
