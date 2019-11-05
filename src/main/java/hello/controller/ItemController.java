package hello.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.json.JSONObject;
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

import com.google.gson.JsonObject;

import hello.model.Item;
import hello.repo.ItemRepo;
import hello.service.ItemService;

@CrossOrigin(origins = {"https://gambit-team12.tk", "http://localhost:4200"})
@RestController
@RequestMapping(value = "/rest/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	@Autowired
	private ItemRepo itemRepo;

	@GetMapping(value = "/all")
	public List<Item> getAllItem() {
		return itemService.getAll();
	}
	
	@GetMapping("/{item_id}")
	public ResponseEntity<Item> findById(@PathVariable int item_id) {
		Optional<Item> item = itemService.findById(item_id);
		if (!item.isPresent()) {
			ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(item.get());
	}
	
	@GetMapping(value = "/all/{username}")
	public List<Object[]> getItemsByUsername(@Valid @PathVariable String username) {
		return itemRepo.findItemsByUsername(username);
	}

	@PostMapping("/create") // Map ONLY POST Requests
	public ResponseEntity create(@RequestBody Item item) {		
//		if (!itemService.findById(item.getItem_id()).isPresent()) {			
//			return ResponseEntity.ok(itemService.saveItem(item));
//		}
//		return null;		
//		if (!itemService.findById(item.getItem_id()).isPresent()) {
//			return ResponseEntity.ok(itemService.saveItem(item));
//		}
//		return null;
		return ResponseEntity.ok(itemService.saveItem(item));
	}
	
	@PutMapping("/update/{item_id}")
	public ResponseEntity<Item> update(@Valid @PathVariable int item_id, @RequestBody Item item) {
		// here must set because person using this api wouldnt know whats the item_id 
		// so the requestbody wont include the item_id, so we must set for them
		item.setItem_id(item_id);
		if (!itemService.findById(item_id).isPresent()) {
//			ResponseEntity.badRequest().build();
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(itemService.saveItem(item));
	}
	
	@DeleteMapping("/delete/{item_id}")
    public ResponseEntity delete(@PathVariable int item_id) {
        if (!itemService.findById(item_id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        itemService.deleteById(item_id);

        return ResponseEntity.ok().build();
    }

}
