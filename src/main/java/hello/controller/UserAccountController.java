package hello.controller;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.catalina.User;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.google.common.hash.Hashing;
import com.google.gson.JsonObject;

import hello.Application;
import hello.JwtGenerator;
import hello.JwtUser;
import hello.model.Admin;
import hello.model.UserAccount;
import hello.repo.UserAccountRepo;
import hello.repo.UserAccountView;
import hello.repo.UserInventoryRepo;
import hello.service.UserAccountService;

@CrossOrigin(origins = { "https://gambit-team12.tk", "http://localhost:4200" })

@RestController
@RequestMapping(value = "/useraccount")
public class UserAccountController {

	@Autowired
	private UserAccountService userService;
	@Autowired
	UserAccountRepo userAccountRepo;
	@Autowired
	UserInventoryRepo userInventoryRepo;

	ViewControllerRegistry registry;

	private JwtGenerator jwtGenerator;

	@Autowired
	private JavaMailSender javaMailSender;

	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);

		mailSender.setUsername("ssdgroup12@gmail.com");
		mailSender.setPassword("dmkimdgswosawvyq");

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");

		return mailSender;
	}

	public UserAccountController(JwtGenerator jwtGenerator) {
		this.jwtGenerator = jwtGenerator;
	}

//	@GetMapping(value = "/all")
//	public List<UserAccount> getAllUsers() {
//		return userService.getAll();
//	}

//	@GetMapping("/{username}")
//	public Object findById(@PathVariable String username) {
//		Object user = userAccountRepo.getUserByUsername(username);
//		return user;
//	}

	@GetMapping("/{username}")
	public List<UserAccountView> findById(@PathVariable String username) {
		List<UserAccountView> json = userAccountRepo.getDetailsByUsername(username);
		return json;
	}

	@PostMapping("/create") // Map ONLY POST Requests
	public Map<String, Object> create(@RequestBody UserAccount userAccount) {
		ResponseEntity<Admin> responseEntity = null;
		Map<String, Object> json = new HashMap();

		// if user dont exist
		if (!userService.findById(userAccount.getUsername()).isPresent()) {
			userAccount.setUsername(userAccount.getUsername());

			// 1. generate salt
			// generate salt value
			String generatedSalt = generateSalt().toString();
			// 2. hash the user's password with the salt (X)
			String password_plus_salt = "" + userAccount.getPassword() + generatedSalt;
			// 3. use sha256 to hash X
			String generatedHash_SHA256 = Hashing.sha256().hashString(password_plus_salt, StandardCharsets.UTF_8)
					.toString();

			userAccount.setPassword_hash(generatedHash_SHA256);
			userAccount.setSalt(generatedSalt.toString());
//			userService.saveUser(userAccount);
			userAccountRepo.createNormalUser(userAccount.getUsername(), generatedHash_SHA256, generatedSalt,
					userAccount.getMobile_number(), userAccount.getEmail(), "active", false);

			// at the same time, create a record for this new user in user_inventory
			userInventoryRepo.createNewRecord(userAccount.getUsername(), 0, 0, false);

			responseEntity = new ResponseEntity<Admin>(HttpStatus.CREATED);

			json.put("created", "true");

		} else {
			responseEntity = new ResponseEntity<Admin>(HttpStatus.BAD_REQUEST);
			json.put("created", "false");

		}

		return json;
	}

	@PostMapping("/login")
	@Transactional
	public Map<String, Object> login2(@RequestBody UserAccount userAccount) {
		Optional<UserAccount> user = userService.findById(userAccount.getUsername());
		Map<String, Object> json = new HashMap();
		ResponseEntity<UserAccount> responseEntity = null;
		HttpHeaders httpHeaders = new HttpHeaders();

		// if user exists
		if (userService.findById(userAccount.getUsername()).isPresent()) {

			UserAccount userInfo = user.get();
			// get user's paswordhash
			String user_password_hash = userInfo.getPassword_hash(); // for comparing later
			// do hashing procedure again with the provided password

			String password_plus_salt = "" + userAccount.getPassword() + userInfo.getSalt();
			// now u hash again
			String generatedHash_SHA256 = Hashing.sha256().hashString(password_plus_salt, StandardCharsets.UTF_8)
					.toString();

			System.out.println("@@@@@@@@@@@@@ " + user_password_hash);
			System.out.println("@@@@@@@@@@@@@ " + generatedHash_SHA256);

			// compare this hash with the user's pw hash
			if (user_password_hash.equals(generatedHash_SHA256)) {
				userAccountRepo.updateUserLoginStatus(userAccount.getUsername(), "online");
				json.put("login", "true");
				responseEntity = new ResponseEntity<UserAccount>(HttpStatus.OK);
//				registry.addViewController("/**").setViewName("forward:/");	

				// GENERATE JWT TOKEN
				String token = jwtGenerator.generateForUser(userAccount).toString();
				json.put("token", token);
				userService.updateUserToken(userAccount.getUsername(), token);

			} else {
				System.out.println("FAILED");
				json.put("login", "false");
//				return ResponseEntity.ok("logged in");
				responseEntity = new ResponseEntity<UserAccount>(HttpStatus.UNAUTHORIZED);
			}
		} else {
			json.put("login", "false");
			responseEntity = new ResponseEntity<UserAccount>(HttpStatus.UNAUTHORIZED);

		}
		return json;
	}

	@PostMapping("/forgetpassword")
	@Transactional
	public Map<String, Object> forgetPassword(@RequestBody UserAccount userAccount) {
//		Optional<UserAccount> user = userService.findById(userAccount.getUsername());
		Map<String, Object> json = new HashMap();
		String getEmailString = userAccountRepo.getEmailByUsername(userAccount.getUsername());
		// if email exists
		if (userAccount.getEmail().equals(getEmailString)) {
			// generate new password
			String reset_password = "WTF";
//			userAccountRepo.updateResetPassword(userAccount.getUsername(), reset_password);
			userService.updateResetPassword(userAccount.getUsername(), reset_password);
			// send email
			sendEmail(userAccount.getEmail(), userAccount.getUsername(), reset_password);
			json.put("reset", "true");
			return json;
		} else {
			json.put("reset", "false");
			return json;
		}
	}

	@PostMapping("/updatepassword")
	@Transactional
	public Map<String, Object> updatePassword(@RequestBody UserAccount userAccount) {
		Map<String, Object> json = new HashMap();
		//check that reset_password field is not null, proves that he has requested to forget/reset password		
		if(!userAccountRepo.checkResetPasswordNull(userAccount.getUsername()).equals(null)) {
			// 1. generate salt
			// generate salt value
			String generatedSalt = generateSalt().toString();
			// 2. hash the user's password with the salt (X)
			String password_plus_salt = "" + userAccount.getPassword() + generatedSalt;
			// 3. use sha256 to hash X
			String generatedHash_SHA256 = Hashing.sha256().hashString(password_plus_salt, StandardCharsets.UTF_8)
					.toString();

			userAccount.setPassword_hash(generatedHash_SHA256);
			userAccount.setSalt(generatedSalt.toString());
			userAccountRepo.updateNewPassword(userAccount.getUsername(), generatedHash_SHA256, generatedSalt);
			json.put("updated", "true");
			return json;
		}		
		else {
			json.put("updated", "false");
			return json;	
		}
		
	}

	public String givenUsingPlainJava_whenGeneratingRandomStringUnbounded_thenCorrect() {
		byte[] array = new byte[7]; // length is bounded by 7
		new Random().nextBytes(array);
		String generatedString = new String(array, Charset.forName("UTF-8"));
		return generatedString;
	}

	void sendEmail(String email, String username, String password) {

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(email);

		msg.setSubject("Reset Password for Gambit");
		msg.setText("Hello " + username + "," + " \n Your new password is: " + password);

		javaMailSender.send(msg);

	}

//	@PostMapping("/logout")
//	@Transactional
//	public Map<String, Object> logout(@RequestBody UserAccount userAccount) {
//		Optional<UserAccount> user = userService.findById(userAccount.getUsername());
//		Map<String, Object> json = new HashMap();
//		// if user exists
//		if (userService.findById(userAccount.getUsername()).isPresent()) {
//			json.put("login", "false");
//			userAccountRepo.updateUserLogoutStatus(userAccount.getUsername(), "active");
//			System.out.println(userAccount.getUsername() + " logged out");	
//			
//		}
//		return json;
//	}

	@PostMapping("/logout/{username}")
	@Transactional
	public Map<String, Object> logout(@PathVariable String username) {
//		Optional<UserAccount> user = userService.findById(username);
		Map<String, Object> json = new HashMap();
		// if user exists
		if (userService.findById(username).isPresent()) {
			json.put("login", "false");
			userAccountRepo.updateUserLogoutStatus(username, "active");
			System.out.println(username + " logged out");
		} else {
			json.put("login", "invalid user");
		}
		return json;
	}

//	@PostMapping
//    public String generate(@RequestBody final JwtUser jwtUser) {
//        return jwtGenerator.generate(jwtUser);
//
//    }

//	@PutMapping("/updateHashSalt/{username}")
//	public ResponseEntity<UserAccount> updateHashSalt(@PathVariable String username,
//			@RequestBody UserAccount userAccount) {
//		if (!userService.findById(username).isPresent()) {
//			ResponseEntity.badRequest().build();
//		}
//
//		return ResponseEntity.ok(userService.saveUser(userAccount));
//	}

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

//	@DeleteMapping("/delete/{username}")
//	public ResponseEntity delete(@PathVariable String username) {
//		if (!userService.findById(username).isPresent()) {
//			ResponseEntity.badRequest().build();
//		}
//		userService.deleteById(username);
//
//		return ResponseEntity.ok().build();
//	}

	public byte[] generateSalt() {
		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[20];
		random.nextBytes(bytes);
		return bytes;
	}

	@Transactional
	@PutMapping("/update/ban/{username}")
	public int banUser(@Valid @PathVariable String username) {
		System.out.println("FIXED RATE BAN USER");
		return userAccountRepo.banUser(username, "inactive");
	}

	@RequestMapping({ "/hello" })
	public String firstPage() {
		return "Hello World";
	}

}
