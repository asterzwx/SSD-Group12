package hello.repo;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.google.gson.JsonObject;

import hello.model.Item;

public interface ItemRepo extends JpaRepository<Item, Integer>{
	

	@Query("SELECT u.item_id, i.name, i.image FROM Item i, UserInventory u " + 
			"WHERE i.item_id = u.item_id AND u.username = :username")			
	List<Object[]> findItemsByUsername(String username);

}
