package hello.model;

import javax.persistence.Column;
import javax.persistence.Id;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class API_Team {
	

	public API_Team() {
		super();
		// TODO Auto-generated constructor stub
	}

	public API_Team(int id, String name, String acronym, String image_url, Videogame videogame) {
		super();
		this.id = id;
		this.name = name;
		this.acronym = acronym;
		this.image_url = image_url;
		this.videogame = videogame;
	}

	@SerializedName("id")
	@Expose
	private int id;
	
	@SerializedName("name")
	@Expose
	private String name;
	
	@SerializedName("acronym")
	@Expose
	private String acronym;
	
	
	@SerializedName("image_url")
	@Expose
	private String image_url;
	
	@SerializedName("current_videogame")
	@Expose
	private Videogame videogame;

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

	public Videogame getVideogame() {
		return videogame;
	}

	public void setVideogame(Videogame videogame) {
		this.videogame = videogame;
	}


	
}
