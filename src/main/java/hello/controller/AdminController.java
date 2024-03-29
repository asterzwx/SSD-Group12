package hello.controller;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

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

import com.google.common.hash.Hashing;

import hello.model.Admin;
import hello.service.AdminService;

@CrossOrigin(origins = "https://gambit-team12.tk")
@RestController
@RequestMapping(value = "/rest/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;

	@GetMapping(value = "/all")
	public List<Admin> getAllAdmin() {
		return adminService.getAll();
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<Admin> findById(@PathVariable String username) {
		Optional<Admin> user = adminService.findById(username);
		if (!user.isPresent()) {
			ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(user.get());
	}

	@PostMapping("/create") // Map ONLY POST Requests
	public ResponseEntity create(@RequestBody Admin adminAccount) {
		// if user dont exist
		if (!adminService.findById(adminAccount.getUsername()).isPresent()) {
			adminAccount.setUsername(adminAccount.getUsername());
			
			//generate salt value
			String generatedSalt = generateSalt().toString();

			//generate password hash value			
			String password_plus_salt = "" + adminAccount.getPassword() + generatedSalt;
			String generatedHash_SHA256 = Hashing.sha256()
					  .hashString(password_plus_salt, StandardCharsets.UTF_8)
					  .toString();
			
			adminAccount.setPassword_hash(generatedHash_SHA256);
			adminAccount.setSalt(generatedSalt);
			return ResponseEntity.ok(adminService.saveUser(adminAccount));
		}
		return null;
	}
	
	@PutMapping("/updateHashSalt/{username}")
	public ResponseEntity<Admin> updateHashSalt(@PathVariable String username, @RequestBody Admin adminAccount) {
		if (!adminService.findById(username).isPresent()) {
			ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok(adminService.saveUser(adminAccount));
	}

	@PutMapping("/update/{username}")
	public ResponseEntity<Admin> update(@PathVariable String username, @RequestBody Admin adminAccount) {
		adminAccount.setUsername(username);
		if (!adminService.findById(username).isPresent()) {
			ResponseEntity.badRequest().build();
		}
		adminAccount.setPassword_hash("updatedhashvaluehere");
		adminAccount.setSalt("updatedsaltvaluehere");
		return ResponseEntity.ok(adminService.saveUser(adminAccount));
	}
	
	@DeleteMapping("/delete/{username}")
    public ResponseEntity delete(@PathVariable String username) {
        if (!adminService.findById(username).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        adminService.deleteById(username);

        return ResponseEntity.ok().build();
    }
	
	public byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        return bytes;
    }



}
