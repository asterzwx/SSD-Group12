package hello.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.model.API_League;
import hello.model.League;
import hello.model.Tournament;

// repo is to save stuff to DB
public interface TournamentRepo extends JpaRepository<Tournament, Integer>{

}
