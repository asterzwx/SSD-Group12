package hello.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.model.Match;
//
public interface MatchRepo extends JpaRepository<Match, Integer>{

}
