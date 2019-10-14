package hello.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.model.UserInventory;

public interface UserInventoryRepo extends JpaRepository<UserInventory, String>{
	

}
