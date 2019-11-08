package hello.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.Nullable;

@Entity
@Table(name = "user_account")
public class UserAccount {

	public UserAccount() {

	}



	public UserAccount(String username, String password, String profile_picture, String mobile_number, String email,
			String status, String password_hash, String salt, String token, boolean role, int reset_password,
			int otp_count, String datetime_locked, String otp) {
		super();
		this.username = username;
		this.password = password;
		this.profile_picture = profile_picture;
		this.mobile_number = mobile_number;
		this.email = email;
		this.status = status;
		this.password_hash = password_hash;
		this.salt = salt;
		this.token = token;
		this.role = role;
		this.reset_password = reset_password;
		this.otp_count = otp_count;
		this.datetime_locked = datetime_locked;
		this.otp = otp;
	}



	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "profile_picture")
	private String profile_picture;

	@Column(name = "mobile_number")
	private String mobile_number;

	@Column(name = "email")
	private String email;

	@Column(name = "status")
	private String status;
	
	@Column(name = "password_hash")
	private String password_hash;

	@Column(name = "salt")
	private String salt;
	
	@Column(name = "token")
	@Nullable
	private String token;
	
	@Column(name = "role")
	@Nullable
	private boolean role;
	
	@Column(name = "reset_password")
	@Nullable
	private int reset_password;
	
	@Column(name = "otp_count")
	@Nullable
	private int otp_count;
	
	@Column(name = "datetime_locked")
	@Nullable
	private String datetime_locked;
	
	@Column(name = "otp")
	@Nullable
	private String otp;
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public String getProfile_picture() {
		return profile_picture;
	}


	public void setProfile_picture(String profile_picture) {
		this.profile_picture = profile_picture;
	}



	public String getMobile_number() {
		return mobile_number;
	}


	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

	public String getPassword_hash() {
		return password_hash;
	}


	public void setPassword_hash(String password_hash) {
		this.password_hash = password_hash;
	}


	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public boolean getRole() {
		return role;
	}


	public void setRole(boolean role) {
		this.role = role;
	}


	public int getReset_password() {
		return reset_password;
	}


	public void setReset_password(int reset_password) {
		this.reset_password = reset_password;
	}


	public int getOtp_count() {
		return otp_count;
	}


	public void setOtp_count(int otp_count) {
		this.otp_count = otp_count;
	}


	public String getDatetime_locked() {
		return datetime_locked;
	}


	public void setDatetime_locked(String datetime_locked) {
		this.datetime_locked = datetime_locked;
	}



	public String getOtp() {
		return otp;
	}



	public void setOtp(String otp) {
		this.otp = otp;
	}


	
	

}
