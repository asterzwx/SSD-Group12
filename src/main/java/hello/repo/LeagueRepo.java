package hello.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.model.League;

public interface LeagueRepo extends JpaRepository<League, Integer>{

}
