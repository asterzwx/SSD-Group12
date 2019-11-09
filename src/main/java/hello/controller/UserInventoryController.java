package hello.controller;

import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hello.model.Match;
import hello.model.UserInventory;
import hello.repo.UserInventoryRepo;
import hello.service.PollService;
import hello.service.UserInventoryService;

@CrossOrigin(origins = { "https://gambit-team12.tk", "http://localhost:4200" })
@RestController
@RequestMapping(value = "/rest/userinventory")
public class UserInventoryController {

	@Autowired
	private UserInventoryService userInventoryService;
	@Autowired
	private UserInventoryRepo userInventoryRepo;

	@GetMapping(value = "/all")
	public List<UserInventory> getAllUserInventories() {
		return userInventoryService.getAll();
	}

//	@GetMapping("/{username}")
//	public ResponseEntity<UserInventory> findById(@PathVariable String username) {
//		Optional<UserInventory> user_inventory = userInventoryService.findById(username);
//		if (!user_inventory.isPresent()) {
//			ResponseEntity.badRequest().build();
//		}
//
//		return ResponseEntity.ok(user_inventory.get());
//	}

	@GetMapping("/{username}")
	public List<UserInventory> getInventoryByUsername(@PathVariable String username) {
		return userInventoryRepo.getItemsOwnedById(username);
	}

	@PostMapping("/create") // Map ONLY POST Requests
	public ResponseEntity create(@RequestBody UserInventory user_inventory) {
//		if (!userInventoryService.findById(user_inventory.getUsername()).isPresent()) {
		return ResponseEntity.ok(userInventoryService.saveUserInventory(user_inventory));
//		}
//		return null;
	}

	@PutMapping("/update/{username}")
	public ResponseEntity<UserInventory> update(@Valid @PathVariable String username,
			@RequestBody UserInventory user_inventory) {
		user_inventory.setUsername(username);
		if (!userInventoryService.findById(username).isPresent()) {
//			ResponseEntity.badRequest().build();
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(userInventoryService.saveUserInventory(user_inventory));
	}

	// end user may not be able to delete because they cant remove/sell/delete their
	// flairs in the app
	@DeleteMapping("/delete/{username}")
	public ResponseEntity delete(@PathVariable String user_inventory) {
		if (!userInventoryService.findById(user_inventory).isPresent()) {
			ResponseEntity.badRequest().build();
		}
		userInventoryService.deleteById(user_inventory);

		return ResponseEntity.ok().build();
	}

	@Transactional
	@PutMapping("/update/allrecords/{points}/{username}")
	public int updateUserPoints(@Valid @PathVariable int points, @PathVariable String username) {
		return userInventoryRepo.updateUserPoints(points, username);
	}

	@Transactional
	@PutMapping("/update/itemsnotinuse/{username}")
	public int updateOtherItemsNotInUse(@Valid @PathVariable String username) {
		return userInventoryRepo.updateOtherItemsNotInUse(false, username);
	}

	@Transactional
	@PutMapping("/update/iteminuse/{username}/{item_id}")
	public int updateItemInUse(@Valid @PathVariable String username, @PathVariable int item_id) {
		return userInventoryRepo.updateUserItemInUse(true, username, item_id);
	}

	@Transactional
	@PutMapping("/useflair/{username}/{item_id}")
	public int useFlair(@Valid @PathVariable String username, @PathVariable int item_id) {
		updateOtherItemsNotInUse(username);
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateItemInUse(username, item_id);
	}

	// check if item id exist in table where username = username
//	@GetMapping("/{item_id}/{username}")
	public boolean itemOwnedByUser(int item_id, String username) {
//		Map<String, Object> json = new HashMap();
		boolean itemOwned = false;
		List<UserInventory> user_inventory = userInventoryRepo.getItemsOwnedById(username);
		for (UserInventory u : user_inventory) {
			if (item_id == u.getItem_id()) {
				itemOwned = true;
				return itemOwned;
			} 
//			else {
//				itemOwned = false;				
//			}
		}
		itemOwned = false;
		return itemOwned;
	}

	@Transactional
	@PostMapping("/buy/{username}/{item_id}/{cost}") // Map ONLY POST Requests
	public Map<String, Object> buy(@PathVariable String username, @PathVariable int item_id, @PathVariable int cost) {
		Map<String, Object> json = new HashMap();
		boolean alreadyOwn = itemOwnedByUser(item_id, username);

		int currentUserPoints = userInventoryRepo.getPointsById(username);
		if (currentUserPoints == 0) {
			json.put("purchase", "false");
			json.put("already_owned", alreadyOwn);
			json.put("user_points", userInventoryRepo.getPointsById(username));
			json.put("item_cost", cost);
			
			return json;
		}
		if (currentUserPoints < cost || alreadyOwn == true) {
			json.put("purchase", "insufficient or owned");
			json.put("already_owned", alreadyOwn);
			json.put("user_points", userInventoryRepo.getPointsById(username));
			json.put("item_cost", cost);
			return json;
		} else {
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ " + currentUserPoints);
			System.out.println("--------------------------------- " + cost);
			
			int latestPoints = currentUserPoints - cost;
			// if purchase success,
			// 1. create new record in user_inventory with the latest points
			UserInventory userInventory = new UserInventory();
			userInventory.setUsername(username);
			userInventory.setPoints(latestPoints);
			userInventory.setItem_id(item_id);
			userInventory.setItem_in_use(false);
//				userInventoryRepo.save(userInventory);
			create(userInventory);
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 2. update all record set points = latestpoints where username = username
			userInventoryRepo.updateUserPoints(latestPoints, username);
			json.put("purchase", "true");
			json.put("already_owned", alreadyOwn);
			json.put("user_points", userInventoryRepo.getPointsById(username));
			json.put("item_cost", cost);
			return json;

		}

//		return json;
	}

}
