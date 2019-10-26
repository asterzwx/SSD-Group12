package hello.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.model.Game;
//
public interface GameRepo extends JpaRepository<Game, Integer>{

}
