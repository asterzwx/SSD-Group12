package hello.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserAccount {

	public UserAccount() {

	}

	
	public UserAccount(String username, String password, String profile_picture, String mobileNumber, String email,
			String status, String password_hash, String salt) {
		super();
		this.username = username;
		this.password = password;
		this.profile_picture = profile_picture;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.status = status;
		this.password_hash = password_hash;
		this.salt = salt;
	}



	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "profile_picture")
	private String profile_picture;

	@Column(name = "mobileNumber")
	private String mobileNumber;

	@Column(name = "email")
	private String email;

	@Column(name = "status")
	private String status;
	
	@Column(name = "password_hash")
	private String password_hash;

	@Column(name = "salt")
	private String salt;

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


	public String getMobileNumber() {
		return mobileNumber;
	}


	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
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

}