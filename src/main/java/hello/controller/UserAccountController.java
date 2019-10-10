package hello.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import hello.model.UserAccount;
import hello.repo.UserAccountRepo;
import hello.service.UserAccountService;

@RestController
@RequestMapping(value = "/rest/useraccount")
public class UserAccountController {

	@Autowired
	private UserAccountService userService;

	@GetMapping(value = "/all")
	public List<UserAccount> getAllUsers() {
		return userService.getAll();
	}

	@GetMapping("/{username}")
	public ResponseEntity<UserAccount> findById(@PathVariable String username) {
		Optional<UserAccount> user = userService.findById(username);
		if (!user.isPresent()) {
			ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(user.get());
	}

	@PostMapping("/create") // Map ONLY POST Requests
	public ResponseEntity create(@RequestBody UserAccount userAccount) {
		// if user dont exist
		if (!userService.findById(userAccount.getUsername()).isPresent()) {
			userAccount.setUsername(userAccount.getUsername());
			userAccount.setPassword_hash("hashvaluehere");
			userAccount.setSalt("saltvaluehere");
			return ResponseEntity.ok(userService.saveUser(userAccount));
		}
		return null;
	}
	
	@PutMapping("/updateHashSalt/{username}")
	public ResponseEntity<UserAccount> updateHashSalt(@PathVariable String username, @RequestBody UserAccount userAccount) {
		if (!userService.findById(username).isPresent()) {
			ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok(userService.saveUser(userAccount));
	}

	@PutMapping("/update/{username}")
	public ResponseEntity<UserAccount> update(@PathVariable String username, @RequestBody UserAccount userAccount) {
		userAccount.setUsername(username);
		if (!userService.findById(username).isPresent()) {
			ResponseEntity.badRequest().build();			
		}
		userAccount.setPassword_hash("updatedhashvaluehere");
		userAccount.setSalt("updatedsaltvaluehere");
		return ResponseEntity.ok(userService.saveUser(userAccount));
	}
	
	@DeleteMapping("/delete/{username}")
    public ResponseEntity delete(@PathVariable String username) {
        if (!userService.findById(username).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        userService.deleteById(username);

        return ResponseEntity.ok().build();
    }

}
