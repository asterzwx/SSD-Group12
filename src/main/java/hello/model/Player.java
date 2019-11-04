package hello.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Null;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.micrometer.core.lang.Nullable;

@Entity
@Table(name = "player")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true, allowSetters = true)
public class Player implements Serializable {
	
	
	
	public Player() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Player(int id, String name, String first_name, String last_name, int current_team, String team_name,
			String videogame, String hometown, String role, String image_url) {
		super();
		this.id = id;
		this.name = name;
		this.first_name = first_name;
		this.last_name = last_name;
		this.current_team = current_team;
		this.team_name = team_name;
		this.videogame = videogame;
		this.hometown = hometown;
		this.role = role;
		this.image_url = image_url;
	}


	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	@Nullable
	private String name;
	
	@Column(name = "first_name")
	@Nullable
	private String first_name;
	
	@Column(name = "last_name")
	@Nullable
	private String last_name;
	
	@Column(name = "current_team")
	@Nullable
	private int current_team;
	
	@Column(name = "team_name")
	@Nullable
	private String team_name;
	
	@Column(name = "videogame")
	@Nullable
	private String videogame;
	
	@Column(name = "hometown")
	@Nullable
	private String hometown;
	
	@Column(name = "role")
	@Nullable
	private String role;
	
	@Column(name = "image_url")
	@Nullable
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

	public String getTeam_name() {
		return team_name;
	}

	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}

	public int getCurrent_team() {
		return current_team;
	}

	public void setCurrent_team(int current_team) {
		this.current_team = current_team;
	}

	public String getVideogame() {
		return videogame;
	}

	public void setVideogame(String videogame) {
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
