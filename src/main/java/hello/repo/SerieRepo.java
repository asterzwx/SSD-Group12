package hello.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.model.Match;
import hello.model.Serie;
//
public interface SerieRepo extends JpaRepository<Serie, Integer>{

}
