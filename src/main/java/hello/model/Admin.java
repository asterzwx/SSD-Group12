package hello.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Admin {
	public Admin() {
	
	}
	
	

	public Admin(String username, String password, String profile_picture, String password_hash, String salt,
			String token) {
		super();
		this.username = username;
		this.password = password;
		this.profile_picture = profile_picture;
		this.password_hash = password_hash;
		this.salt = salt;
		this.token = token;
	}



	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "profile_picture")
	private String profile_picture;
	
	@Column(name = "password_hash")
	private String password_hash;
	
	@Column(name = "salt")
	private String salt;
	
	@Column(name = "token")
	private String token;

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
	
	

}
