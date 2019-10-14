package hello.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.model.Item;

public interface ItemRepo extends JpaRepository<Item, Integer>{
	
//	Optional<Item> findById(int item_id);

}
