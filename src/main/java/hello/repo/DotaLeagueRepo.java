package hello.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.model.API_League;
import hello.model.League;

// repo is to save stuff to DB
public interface DotaLeagueRepo extends JpaRepository<League, Integer>{

}
