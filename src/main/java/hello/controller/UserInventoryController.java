package hello.controller;

import java.util.List;
import java.util.Optional;

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

import hello.model.UserInventory;
import hello.service.PollService;
import hello.service.UserInventoryService;

@CrossOrigin(origins = "https://gambit-team12.tk")
@RestController
@RequestMapping(value = "/rest/userinventory")
public class UserInventoryController {
	
	@Autowired
	private UserInventoryService userInventoryService;

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
	public ResponseEntity<UserInventory> update(@Valid @PathVariable String username, @RequestBody UserInventory user_inventory) {
		user_inventory.setUsername(username);
		if (!userInventoryService.findById(username).isPresent()) {
//			ResponseEntity.badRequest().build();
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(userInventoryService.saveUserInventory(user_inventory));
	}
	
	// end user may not be able to delete because they cant remove/sell/delete their flairs in the app
	@DeleteMapping("/delete/{username}")
    public ResponseEntity delete(@PathVariable String user_inventory) {
        if (!userInventoryService.findById(user_inventory).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        userInventoryService.deleteById(user_inventory);

        return ResponseEntity.ok().build();
    }

}
