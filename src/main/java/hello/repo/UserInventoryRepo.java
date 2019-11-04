package hello.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hello.model.UserInventory;
import hello.model.UserPrediction;

public interface UserInventoryRepo extends JpaRepository<UserInventory, String>{
	@Modifying
	@Query("UPDATE UserInventory i SET i.points = :points WHERE i.username = :username")
	int updateUserPoints(@Param("points") int points,
			@Param("username") String username);

	@Modifying
	@Query("UPDATE UserInventory i SET i.item_in_use = :tinyint WHERE i.username = :username ")
	int updateOtherItemsNotInUse(@Param("tinyint") Boolean tinyint,
			@Param("username") String username);
	
	@Modifying
	@Query("UPDATE UserInventory i SET i.item_in_use = :tinyint WHERE i.username = :username "
			+ "AND i.item_id = :item_id")
	int updateUserItemInUse(@Param("tinyint") boolean tinyint,
			@Param("username") String username, @Param("item_id") int item_id);
	
	@Query("SELECT i FROM UserInventory i WHERE i.username = :username")
	List<UserInventory> getUsernamesById(@Param("username") String username);
	
	@Query("SELECT i FROM UserInventory i WHERE i.username = :username")
	List<UserInventory> getItemsOwnedById(@Param("username") String username);
	
	@Query("SELECT i.points as points FROM UserInventory i WHERE i.username = :username")
	int getPointsById(@Param("username") String username);
	
	@Modifying
    @Query(value = "insert into user_inventory (username,points,item_id,item_in_use) VALUES "
    		+ "(:username,:points,:item_id,:item_in_use)", nativeQuery = true)
    @Transactional
    void createNewRecord(@Param("username") String username, @Param("points") int id,
    		@Param("item_id") int item_id, @Param("item_in_use") boolean item_in_use);
}
