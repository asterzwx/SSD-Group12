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

	public UserAccount(String username, String password, String profilePicture, String mobileNumber, String email,
			String status, String passwordHash, String salt) {
		this.username = username;
		this.password = password;
		this.profilePicture = profilePicture;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.status = status;
		this.passwordHash = passwordHash;
		this.salt = salt;
	}

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "profilePicture")
	private String profilePicture;

	@Column(name = "mobileNumber")
	private String mobileNumber;

	@Column(name = "email")
	private String email;

	@Column(name = "status")
	private String status;

	@Column(name = "passwordHash")
	private String passwordHash;

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

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
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

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

}
