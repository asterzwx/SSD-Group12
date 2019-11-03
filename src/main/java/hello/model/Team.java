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
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.micrometer.core.lang.Nullable;

@Entity
@Table(name = "team")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true, allowSetters = true)
public class Team implements Serializable {
	
	
	
	public Team() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Team(int id, String name, String acronym, String image_url, String videogame) {
		super();
		this.id = id;
		this.name = name;
		this.acronym = acronym;
		this.image_url = image_url;
		this.videogame = videogame;
	}


	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	@Nullable
	private String name;
	
	@Column(name = "acronym")
	@Nullable
	private String acronym;
	
	@Column(name = "image_url")
	@Nullable
	private String image_url;
	
	@Column(name = "videogame")
	@Nullable
	private String videogame;

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

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}


	public String getAcronym() {
		return acronym;
	}


	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}


	public String getVideogame() {
		return videogame;
	}


	public void setVideogame(String videogame) {
		this.videogame = videogame;
	}
	
	
	
}
