package hello.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

@CrossOrigin(origins = "https://gambit-team12.tk")
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

	@GetMapping("/{username}")
	public ResponseEntity<UserInventory> findById(@PathVariable String username) {
		Optional<UserInventory> user_inventory = userInventoryService.findById(username);
		if (!user_inventory.isPresent()) {
			ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(user_inventory.get());
	}

	@PostMapping("/create") // Map ONLY POST Requests
	public ResponseEntity create(@RequestBody UserInventory user_inventory) {
		if (!userInventoryService.findById(user_inventory.getUsername()).isPresent()) {
			return ResponseEntity.ok(userInventoryService.saveUserInventory(user_inventory));
		}
		return null;
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

	// check if item id exist in table where username = username
	@GetMapping("/{item_id}/{username}")
	public boolean itemOwnedByUser(@PathVariable int item_id, @PathVariable String username) {
		Map<String, Object> json = new HashMap();
		boolean itemOwned = false;
		List<UserInventory> user_inventory = userInventoryRepo.getItemsOwnedById(username);
		for (UserInventory u : user_inventory) {
			if (item_id == u.getItem_id()) {
				itemOwned = true;
			} else {
				itemOwned = false;
			}
		}
		return itemOwned;
	}

	@Transactional
	@PostMapping("/buy/{username}/{item_id}/{item_cost}") // Map ONLY POST Requests
	public Map<String, Object> buy(@Valid @PathVariable String username, @Valid @PathVariable int item_id,
			@Valid @PathVariable int item_cost, @RequestBody UserInventory user_inventory) {
		Map<String, Object> json = new HashMap();

		int currentUserPoints = user_inventory.getPoints();

		if (currentUserPoints < item_cost || itemOwnedByUser(item_id, username) == true) {
			json.put("purchase", "false");
		}
		else {
			int latestPoints = currentUserPoints - item_cost;
			// if purchase success,
			// 1. create new record in user_inventory with the latest points
			// 2. update all record set points = latestpoints where username = username

			user_inventory.setPoints(latestPoints);

			json.put("purchase", "true");
		}

		return json;
	}

}
