package hello.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.model.Match;
import hello.model.Opponent;
//
public interface OpponentRepo extends JpaRepository<Opponent, Integer>{

}
