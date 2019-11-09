package hello.controller;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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

	Properties props;

	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static SecureRandom rnd = new SecureRandom();

	boolean otpEnabled = true;

	int emailSentCount = 0;

	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);

		mailSender.setUsername("ssdgroup12@gmail.com");
		mailSender.setPassword("dmkimdgswosawvyq");

		props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");

		return mailSender;
	}

	public UserAccountController(JwtGenerator jwtGenerator) {
		this.jwtGenerator = jwtGenerator;
	}

	@GetMapping(value = "/all")
	public List<UserAccountView> getAllUsers() {
		return userAccountRepo.getAllUsers();
	}

	@GetMapping("/{username}")
	public Map<String, Object> findById(@PathVariable String username) {
		Map<String, Object> json = new HashMap();
		for (UserAccountView u : userAccountRepo.getDetailsByUsername3(username)) {
			json.put("username", u.getUsername());
			json.put("mobile_number", u.getMobile_number());
			json.put("email", u.getEmail());
			json.put("reset_password", u.getReset_password());
			json.put("datetime_locked", u.getDatetime_locked());
		}

		return json;
	}

//	@GetMapping("/{username}")
//	public List<UserAccountView> findById(@PathVariable String username) {
//		List<UserAccountView> json = userAccountRepo.getDetailsByUsername(username);	
//		return json;
//	}

	@PostMapping("/create") // Map ONLY POST Requests
	public Map<String, Object> createNormalUser(@RequestBody UserAccount userAccount) {
		ResponseEntity<Admin> responseEntity = null;
		Map<String, Object> json = new HashMap();
		boolean regexPassed = false;
		// check regex first
		RegexChecker regexChecker = new RegexChecker();
		if (regexChecker.validateInputs(userAccount.getUsername(), userAccount.getEmail(),
				userAccount.getMobile_number(), userAccount.getPassword(), userAccount.getPassword())) {
			regexPassed = true;
		} else {
			regexPassed = false;
			json.put("created", "false");

		}
		for (UserAccount u : userAccountRepo.getAllUserDetails()) {
			if (userAccount.getEmail().equals(u.getEmail())) {
				json.put("created", "email exists");
				return json;
			} else {
				// if user dont exist
				if (!userService.findById(userAccount.getUsername()).isPresent() && regexPassed == true) {
//			userAccount.setUsername(userAccount.getUsername());

					// 1. generate salt
					// generate salt value
					String generatedSalt = generateSalt().toString();
					// 2. hash the user's password with the salt (X)
					String password_plus_salt = "" + userAccount.getPassword() + generatedSalt;
					// 3. use sha256 to hash X
					String generatedHash_SHA256 = Hashing.sha256()
							.hashString(password_plus_salt, StandardCharsets.UTF_8).toString();

					userAccount.setPassword_hash(generatedHash_SHA256);
					userAccount.setSalt(generatedSalt.toString());
//			userService.saveUser(userAccount);
					userAccountRepo.createUser(userAccount.getUsername(), generatedHash_SHA256, generatedSalt,
							userAccount.getMobile_number(), userAccount.getEmail(), "active", false);

					// at the same time, create a record for this new user in user_inventory
					userInventoryRepo.createNewRecord(userAccount.getUsername(), 500, 0, false);

//			responseEntity = new ResponseEntity<Admin>(HttpStatus.CREATED);

					json.put("created", "true");
					return json;

				} else {
//			responseEntity = new ResponseEntity<Admin>(HttpStatus.BAD_REQUEST);
					json.put("created", "false");

				}
			}

		}

		return json;
	}

	@PostMapping("/create/admin") // Map ONLY POST Requests
	public Map<String, Object> createAdminUser(@RequestBody UserAccount userAccount) {
		ResponseEntity<Admin> responseEntity = null;
		Map<String, Object> json = new HashMap();
		boolean regexPassed = false;
		// check regex first
		RegexChecker regexChecker = new RegexChecker();
		if (regexChecker.validateInputs(userAccount.getUsername(), userAccount.getEmail(),
				userAccount.getMobile_number(), userAccount.getPassword(), userAccount.getPassword())) {
			regexPassed = true;
		} else {
			regexPassed = false;
			json.put("created", "false");

		}
		for (UserAccount u : userAccountRepo.getAllUserDetails()) {
			if (userAccount.getEmail() != u.getEmail()) {
				// if user dont exist
				if (!userService.findById(userAccount.getUsername()).isPresent() && regexPassed == true) {
					userAccount.setUsername(userAccount.getUsername());

					// 1. generate salt
					// generate salt value
					String generatedSalt = generateSalt().toString();
					// 2. hash the user's password with the salt (X)
					String password_plus_salt = "" + userAccount.getPassword() + generatedSalt;
					// 3. use sha256 to hash X
					String generatedHash_SHA256 = Hashing.sha256()
							.hashString(password_plus_salt, StandardCharsets.UTF_8).toString();

					userAccount.setPassword_hash(generatedHash_SHA256);
					userAccount.setSalt(generatedSalt.toString());
//					userService.saveUser(userAccount);
					userAccountRepo.createUser(userAccount.getUsername(), generatedHash_SHA256, generatedSalt,
							userAccount.getMobile_number(), userAccount.getEmail(), "active", true);

					// at the same time, create a record for this new user in user_inventory
					userInventoryRepo.createNewRecord(userAccount.getUsername(), 500, 0, false);

					responseEntity = new ResponseEntity<Admin>(HttpStatus.CREATED);

					json.put("created", "true");

				} else {
					responseEntity = new ResponseEntity<Admin>(HttpStatus.BAD_REQUEST);
					json.put("created", "false");

				}
			} else {
				responseEntity = new ResponseEntity<Admin>(HttpStatus.BAD_REQUEST);
				json.put("created", "false");
			}
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

		String status = userAccountRepo.getStatusByUsername(userAccount.getUsername());
		// if user exists
		if (userService.findById(userAccount.getUsername()).isPresent() && !status.equals("locked")) {
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

				responseEntity = new ResponseEntity<UserAccount>(HttpStatus.OK);
//				registry.addViewController("/**").setViewName("forward:/");	

				// GENERATE JWT TOKEN
				String token = jwtGenerator.generateForUser(userAccount).toString();
				json.put("token", "Bearer " + token);
				userService.updateUserToken(userAccount.getUsername(), token);

				// if reset_password not null means requested new password but havent change to
				// new password
				if (!(userAccountRepo.checkResetPasswordNull(userAccount.getUsername()) == 0)) {
					json.put("login", "resetted");
					userAccountRepo.updateResetPassword(userAccount.getUsername(), 0);

				} else {
					json.put("login", "true");
				}

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

	@PostMapping("/sendemailotp")
	@Transactional
	public Map<String, Object> sendEmailOTP(@RequestBody UserAccount userAccount) {

		Map<String, Object> json = new HashMap();

		int count = userAccountRepo.getCurrentOTPCount(userAccount.getUsername());

		if (count >= 3) {
			otpEnabled = false;
		} else {
			otpEnabled = true;
		}
		String email = userAccountRepo.getEmailByUsername(userAccount.getUsername());
		if (count < 3 && otpEnabled == true) {

			// get current user otp count
			// once email is sent, run timer and update user's otp count
			timerCheckUserOTPStatus();

			// generate new password
			String otp = get6DigitOTP();
//				userAccountRepo.updateResetPassword(userAccount.getUsername(), reset_password);
//				 send email
			sendEmail_OTP(email, userAccount.getUsername(), otp);

			count = count + 1;
			setEmailSentCount(count);
			userAccountRepo.updateOTPCount(count, userAccount.getUsername());
			userAccountRepo.updateOTP(otp, userAccount.getUsername());

			json.put("email_sent", "true");

			timerMakeOTPExpire(userAccount.getUsername());

			return json;
		} else {
			userAccountRepo.updateStatus("locked", userAccount.getUsername());

			String timeString = ZonedDateTime // Represent a moment as perceived in the wall-clock time used by the
												// people of a particular region ( a time zone).
					.now( // Capture the current moment.
							ZoneId.of("Asia/Singapore") // Specify the time zone using proper Continent/Region name.
														// Never use 3-4 character pseudo-zones such as PDT, EST, IST.
					) // Returns a `ZonedDateTime` object.
					.format( // Generate a `String` object containing text representing the value of our
								// date-time object.
							DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

			System.out.println("OTP SENT");
			System.out.println(timeString);

			userAccountRepo.lockAccount(timeString, userAccount.getUsername());
			json.put("email_sent", "locked");
//			json.put("email_sent", "false");
			return json;
		}

	}

	@PostMapping("/forgetpassword")
	@Transactional
	public Map<String, Object> forgetPassword(@RequestBody UserAccount userAccount) {
//		newTime = System.currentTimeMillis() + 5000;

//		Optional<UserAccount> user = userService.findById(userAccount.getUsername());
		Map<String, Object> json = new HashMap();
		String getEmailString = userAccountRepo.getEmailByUsername(userAccount.getUsername());
		if (userAccount.getOtp_count() >= 3) {
			otpEnabled = false;
		}

		if (userAccount.getEmail().equals(getEmailString) && userAccount.getOtp_count() < 3 && otpEnabled == true) {

			// get current user otp count
			int count = userAccountRepo.getCurrentOTPCount(userAccount.getUsername());
			// once email is sent, run timer and update user's otp count
//			timerCheckUserOTPStatus(count);

			// generate new password
			String reset_password = getRandomNumberString(8);
//				userAccountRepo.updateResetPassword(userAccount.getUsername(), reset_password);
//				 send email
			sendEmail(userAccount.getEmail(), userAccount.getUsername(), reset_password);

			// then, hash this new password as per normal
			// generate salt value
			String generatedSalt = generateSalt().toString();
			// 2. hash the user's password with the salt (X)
			String password_plus_salt = "" + reset_password + generatedSalt;
			// 3. use sha256 to hash X
			String generatedHash_SHA256 = Hashing.sha256().hashString(password_plus_salt, StandardCharsets.UTF_8)
					.toString();

			// replace old password_hash and salt
			userAccountRepo.updateNewPassword(userAccount.getUsername(), generatedHash_SHA256, generatedSalt, 1);

			count = count + 1;
			setEmailSentCount(count);
			userAccountRepo.updateOTPCount(count, userAccount.getUsername());
//			userAccountRepo.updateOTP(reset_password, userAccount.getUsername());

			json.put("email_sent", "true");

			timerMakeOTPExpire(userAccount.getUsername());

			return json;
		} else {
			userAccountRepo.updateStatus("locked", userAccount.getUsername());

			String timeString = ZonedDateTime // Represent a moment as perceived in the wall-clock time used by the
												// people of a particular region ( a time zone).
					.now( // Capture the current moment.
							ZoneId.of("Asia/Singapore") // Specify the time zone using proper Continent/Region name.
														// Never use 3-4 character pseudo-zones such as PDT, EST, IST.
					) // Returns a `ZonedDateTime` object.
					.format( // Generate a `String` object containing text representing the value of our
								// date-time object.
							DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

			System.out.println("!!!!!!!!!!!!!!!");
			System.out.println(timeString);

			userAccountRepo.lockAccount(timeString, userAccount.getUsername());
			json.put("email_sent", "locked");
//			json.put("email_sent", "false");
			return json;
		}

	}

	// when changing new password
	@PostMapping("/updatepassword")
	@Transactional
	public Map<String, Object> updatePassword(@RequestBody UserAccount userAccount) {
		Map<String, Object> json = new HashMap();
		// check that reset_password field is not null, proves that he has requested to
		// forget/reset password

		// get current pw
		String currentPw = userAccountRepo.getPasswordHashOnlyByUsername(userAccount.getUsername());
		// hsahed new pw

		String newPw = userAccount.getPassword();
		// generate salt value
		String generatedSalt_newPw = userAccountRepo.getSaltOnlyByUsername(userAccount.getUsername());
		// 2. hash the user's password with OLD SALT
		String password_plus_salt_newPw = "" + userAccount.getPassword() + generatedSalt_newPw;
		// 3. use sha256 to hash X
		String generatedHash_SHA256_newPw = Hashing.sha256()
				.hashString(password_plus_salt_newPw, StandardCharsets.UTF_8).toString();
		newPw = generatedHash_SHA256_newPw;

		if (!currentPw.equals(newPw)) {
			try {
				if (!(userAccountRepo.checkResetPasswordNull(userAccount.getUsername()) == 0)) {
					// 1. generate salt
					// generate salt value
					String generatedSalt = generateSalt().toString();
					// 2. hash the user's password with the salt (X)
					String password_plus_salt = "" + userAccount.getPassword() + generatedSalt;
					// 3. use sha256 to hash X
					String generatedHash_SHA256 = Hashing.sha256()
							.hashString(password_plus_salt, StandardCharsets.UTF_8).toString();

					userAccount.setPassword_hash(generatedHash_SHA256);
					userAccount.setSalt(generatedSalt.toString());
					userAccountRepo.updateNewPassword(userAccount.getUsername(), generatedHash_SHA256, generatedSalt,
							0);
					// update reset_password to null
//					userService.updateResetPassword(userAccount.getUsername(), null);
					json.put("updated", "true");
					return json;
				} else {
					json.put("updated", "false");
					return json;
				}

			} catch (Exception e) {
				json.put("updated", "false");
			}
		} else {
			json.put("updated", "false");
		}
		return json;
	}

	@PutMapping("/changepassword")
	@Transactional
	public Map<String, Object> changePassword(@RequestBody UserAccount userAccount) {
		Map<String, Object> json = new HashMap();

		// get current pw
		String currentPw = userAccountRepo.getPasswordHashOnlyByUsername(userAccount.getUsername());
		// hsahed new pw

		String newPw = userAccount.getPassword();
		// generate salt value
		String generatedSalt_newPw = userAccountRepo.getSaltOnlyByUsername(userAccount.getUsername());
		// 2. hash the user's password with the salt (X)
		String password_plus_salt_newPw = "" + userAccount.getPassword() + generatedSalt_newPw;
		// 3. use sha256 to hash X
		String generatedHash_SHA256_newPw = Hashing.sha256()
				.hashString(password_plus_salt_newPw, StandardCharsets.UTF_8).toString();
		newPw = generatedHash_SHA256_newPw;

		if (!currentPw.equals(newPw)) {
			try {
				if (userAccountRepo.checkResetPasswordNull(userAccount.getUsername()) == 0) {
					// 1. generate salt
					// generate salt value
					String generatedSalt = generateSalt().toString();
					// 2. hash the user's password with the salt (X)
					String password_plus_salt = "" + userAccount.getPassword() + generatedSalt;
					// 3. use sha256 to hash X
					String generatedHash_SHA256 = Hashing.sha256()
							.hashString(password_plus_salt, StandardCharsets.UTF_8).toString();

					userAccount.setPassword_hash(generatedHash_SHA256);
					userAccount.setSalt(generatedSalt.toString());
					userAccountRepo.updateNewPassword(userAccount.getUsername(), generatedHash_SHA256, generatedSalt,
							0);
					// update reset_password to null
//							userService.updateResetPassword(userAccount.getUsername(), null);
					json.put("updated", "true");
					return json;
				} else {
					json.put("updated", "false");
					return json;
				}

			} catch (Exception e) {
				json.put("updated", "false");
			}
		} else {
			json.put("updated", "false");
		}
		return json;

	}

	public static String getRandomNumberString(int len) {
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}

	public static String get6DigitOTP() {
		// It will generate 6 digit random Number.
		// from 0 to 999999
		Random rnd = new Random();
		int number = rnd.nextInt(999999);

		// this will convert any number sequence into 6 character.
		return String.format("%06d", number);
	}

	public void sendEmail(String email, String username, String password) {

		// sets SMTP server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", 587);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("ssdgroup12@gmail.com", "dmkimdgswosawvyq");
			}
		};

		Session session = Session.getInstance(properties, auth);

		// creates a new e-mail message
		Message msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress("ssdgroup12@gmail.com"));
			InternetAddress[] toAddresses = { new InternetAddress(email) };
			msg.setRecipients(Message.RecipientType.TO, toAddresses);
			msg.setSubject("GAMBIT Reset Password");
			msg.setSentDate(new Date());
			// set plain text message
			EmailTemplate emailTemplate = new EmailTemplate();
			msg.setContent(emailTemplate.template(password), "text/html");
		} catch (Exception e) {
			// TODO: handle exception
		}

		// sends the e-mail
		try {
			Transport.send(msg);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void sendEmail_OTP(String email, String username, String password) {

		// sets SMTP server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", 587);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("ssdgroup12@gmail.com", "dmkimdgswosawvyq");
			}
		};

		Session session = Session.getInstance(properties, auth);

		// creates a new e-mail message
		Message msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress("ssdgroup12@gmail.com"));
			InternetAddress[] toAddresses = { new InternetAddress(email) };
			msg.setRecipients(Message.RecipientType.TO, toAddresses);
			msg.setSubject("GAMBIT OTP");
			msg.setSentDate(new Date());
			// set plain text message
			EmailTemplate emailTemplate = new EmailTemplate();
			msg.setContent(emailTemplate.template_otp(password), "text/html");
		} catch (Exception e) {
			// TODO: handle exception
		}

		// sends the e-mail
		try {
			Transport.send(msg);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@PostMapping("/logout/{username}")
	@Transactional
	public Map<String, Object> logout(@PathVariable String username) {
//		Optional<UserAccount> user = userService.findById(username);
		Map<String, Object> json = new HashMap();
		// if user exists
		if (userService.findById(username).isPresent()) {
			json.put("login", "false");
			userAccountRepo.updateUserLogoutStatus(username, "active");
			userAccountRepo.updateOTP("0", username);
			userAccountRepo.updateResetPassword(username, 0);
			userAccountRepo.updateOTPCount(0, username);
			System.out.println(username + " logged out");
		} else {
			json.put("login", "invalid user");
		}
		return json;
	}

	@PostMapping("/verify/{username}/{otp}")
	@Transactional
	public Map<String, Object> verifyOTP(@PathVariable String username, @PathVariable String otp) {
//		Optional<UserAccount> user = userService.findById(username);
		Map<String, Object> json = new HashMap();

		if (userAccountRepo.getStatusByUsername(username).equals("locked")) {
			json.put("verified", "locked");
			return json;
		}
		if (userAccountRepo.getOTPByUsername(username).equals(otp)) {
			userAccountRepo.updateOTP("0", username);
			userAccountRepo.updateResetPassword(username, 0);
			userAccountRepo.updateOTPCount(0, username);
			json.put("verified", "true");
			return json;
		} else {
			json.put("verified", "false");
			return json;
		}

	}

	public void timerCheckUserOTPStatus() {
		long current = System.currentTimeMillis();

		TimerTask repeatedTask = new TimerTask() {
			public void run() {

				System.out.println("Task performed on " + new Date());
				// lock user out when count reaches 3
				if (getEmailSentCount() >= 3) {
					otpEnabled = false;
					System.out.println("OTP ENABLED FALSE");
				} else {
					otpEnabled = true;
					System.out.println("OTP ENABLED TRUE");
				}

				// unlock account
				System.out.println("!!!! UNLOCKING ACCOUNTS ");
				userAccountRepo.unlockAccounts();
			}
		};
		Timer timer = new Timer("Timer");

		long delay = 1000L;
		long period = 1000L;
		timer.scheduleAtFixedRate(repeatedTask, delay, period);
	}

	public void timerMakeOTPExpire(String username) {
		long current = System.currentTimeMillis();

		TimerTask repeatedTask = new TimerTask() {
			public void run() {

				System.out.println("Task performed on " + new Date());

				// unlock account
				System.out.println("##### EXPIRING OTP ");
				userAccountRepo.makeOTPExpire(username);
			}
		};
		Timer timer = new Timer("Timer");

		long delay = 50000L;
		long period = 50000L;
		timer.scheduleAtFixedRate(repeatedTask, delay, period);
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

	public int getEmailSentCount() {
		return emailSentCount;
	}

	public void setEmailSentCount(int emailSentCount) {
		this.emailSentCount = emailSentCount;
	}

}
