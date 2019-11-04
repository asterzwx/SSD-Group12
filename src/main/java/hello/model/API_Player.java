package hello.model;

import javax.persistence.Column;
import javax.persistence.Id;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class API_Player {	

	public API_Player() {
		super();
		// TODO Auto-generated constructor stub
	}

	public API_Player(int id, String name, String first_name, String last_name, Team current_team,
			API_Videogame videogame, String hometown, String role, String image_url) {
		super();
		this.id = id;
		this.name = name;
		this.first_name = first_name;
		this.last_name = last_name;
		this.current_team = current_team;
		this.videogame = videogame;
		this.hometown = hometown;
		this.role = role;
		this.image_url = image_url;
	}

	@SerializedName("id")
	@Expose
	private int id;
	
	@SerializedName("name")
	@Expose
	private String name;
	
	@SerializedName("first_name")
	@Expose
	private String first_name;
	
	@SerializedName("last_name")
	@Expose
	private String last_name;
	
	
	@SerializedName("current_team")
	@Expose
	private Team current_team;
	
	@SerializedName("current_videogame")
	@Expose
	private API_Videogame videogame;
	
	@SerializedName("hometown")
	@Expose
	private String hometown;
	
	@SerializedName("role")
	@Expose
	private String role;
	
	@SerializedName("image_url")
	@Expose
	private String image_url;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public Team getCurrent_team() {
		return current_team;
	}

	public void setCurrent_team(Team current_team) {
		this.current_team = current_team;
	}

	public API_Videogame getVideogame() {
		return videogame;
	}

	public void setVideogame(API_Videogame videogame) {
		this.videogame = videogame;
	}

	public String getHometown() {
		return hometown;
	}

	public void setHometown(String hometown) {
		this.hometown = hometown;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}


	
}
