package hello.repo;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.google.gson.JsonObject;

import hello.model.Item;

public interface ItemRepo extends JpaRepository<Item, Integer>{
	

	@Query("SELECT u.item_id as item_id, i.name as name, i.image as image, i.cost as cost FROM Item i, UserInventory u " + 
			"WHERE i.item_id = u.item_id AND u.username = :username")			
	List<Map<String, String>> findItemsByUsername(String username);
	
	@Query("SELECT i FROM Item i WHERE i.item_id = :item_id")			
	List<Item> getItemById(@Param("item_id") int item_id);

}
