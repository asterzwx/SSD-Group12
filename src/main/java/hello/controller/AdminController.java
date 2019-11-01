package hello.controller;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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

import hello.JwtGenerator;
import hello.model.Admin;
import hello.model.UserAccount;
import hello.repo.AdminRepo;
import hello.repo.AdminView;
import hello.repo.UserAccountView;
import hello.service.AdminService;

@CrossOrigin(origins = "https://gambit-team12.tk")
@RestController
@RequestMapping(value = "/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	@Autowired
	AdminRepo adminRepo;
	
	private JwtGenerator jwtGenerator;


//	@GetMapping(value = "/all")
//	public List<Admin> getAllAdmin() {
//		return adminService.getAll();
//	}
	
	@GetMapping("/{username}")
	public List<AdminView> findById(@PathVariable String username) {
		List<AdminView> json = adminRepo.getDetailsByUsername(username);
		return json;
	}
	

	@PostMapping("/create") // Map ONLY POST Requests
	public Map<String, Object> create(@RequestBody Admin adminAccount) {
		ResponseEntity<Admin> responseEntity = null;
		Map<String, Object> json = new HashMap();

		// if user dont exist
		if (!adminService.findById(adminAccount.getUsername()).isPresent()) {
			adminAccount.setUsername(adminAccount.getUsername());

			// generate salt value
			String generatedSalt = generateSalt().toString();

			// generate password hash value
			String password_plus_salt = "" + adminAccount.getPassword() + generatedSalt;
			String generatedHash_SHA256 = Hashing.sha256().hashString(password_plus_salt, StandardCharsets.UTF_8)
					.toString();

			adminAccount.setPassword_hash(generatedHash_SHA256);
			adminAccount.setSalt(generatedSalt);
			adminService.saveUser(adminAccount);
//			return ResponseEntity.ok(adminService.saveUser(adminAccount));
			responseEntity = new ResponseEntity<Admin>(HttpStatus.CREATED);
			json.put("created", "true");

		} else {
			responseEntity = new ResponseEntity<Admin>(HttpStatus.BAD_REQUEST);
			json.put("created", "false");
		}
		return json;
	}

	@PostMapping("/login")
	public ResponseEntity<Admin> login(@RequestBody Admin adminAccount) {
		Optional<Admin> user = adminService.findById(adminAccount.getUsername());
		Map<String, Object> json = new HashMap();
		ResponseEntity<Admin> responseEntity = null;
		HttpHeaders httpHeaders = new HttpHeaders();

		// if user exists
		if (adminService.findById(adminAccount.getUsername()).isPresent()) {

			Admin userInfo = user.get();
			// get user's paswordhash
			String user_password_hash = userInfo.getPassword_hash(); // for comparing later
			// do hashing procedure again with the provided password

			String password_plus_salt = "" + adminAccount.getPassword() + userInfo.getSalt();
			// now u hash again
			String generatedHash_SHA256 = Hashing.sha256().hashString(password_plus_salt, StandardCharsets.UTF_8)
					.toString();

			System.out.println("@@@@@@@@@@@@@ " + user_password_hash);
			System.out.println("@@@@@@@@@@@@@ " + generatedHash_SHA256);

			// compare this hash with the user's pw hash
			if (user_password_hash.equals(generatedHash_SHA256)) {
				json.put("login", "true");
				responseEntity = new ResponseEntity<Admin>(HttpStatus.OK);
				
				//GENERATE JWT TOKEN
				String token = jwtGenerator.generateForAdmin(adminAccount).toString();
				json.put("token", token);
				adminService.updateAdminToken(adminAccount.getUsername(), token);
				
			} else {
				json.put("login", "false");
				responseEntity = new ResponseEntity<Admin>(HttpStatus.UNAUTHORIZED);
			}
		} else {
			json.put("login", "false");
			responseEntity = new ResponseEntity<Admin>(HttpStatus.UNAUTHORIZED);

		}
		return responseEntity;

	}

	@PostMapping("/logout")
	@Transactional
	public Map<String, Object> logout(@RequestBody Admin adminAccount) {
		Optional<Admin> user = adminService.findById(adminAccount.getUsername());
		Map<String, Object> json = new HashMap();

		// if user exists
		if (adminService.findById(adminAccount.getUsername()).isPresent()) {
			json.put("login", "false");
		}
		return json;

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

//	@DeleteMapping("/delete/{username}")
//	public ResponseEntity delete(@PathVariable String username) {
//		if (!adminService.findById(username).isPresent()) {
//			ResponseEntity.badRequest().build();
//		}
//		adminService.deleteById(username);
//
//		return ResponseEntity.ok().build();
//	}

	public byte[] generateSalt() {
		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[20];
		random.nextBytes(bytes);
		return bytes;
	}

}
