package hello.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Videogame {
	public Videogame() {
		// TODO Auto-generated constructor stub
	}

	public Videogame(Object currentVersion, Integer id, String name, String slug) {
		super();
		this.currentVersion = currentVersion;
		this.id = id;
		this.name = name;
		this.slug = slug;
	}

	@SerializedName("current_version")
	@Expose
	private Object currentVersion;

	@SerializedName("id")
	@Expose
	private Integer id;

	@SerializedName("name")
	@Expose
	private String name;

	@SerializedName("slug")
	@Expose
	private String slug;

	public Object getCurrentVersion() {
		return currentVersion;
	}

	public void setCurrentVersion(Object currentVersion) {
		this.currentVersion = currentVersion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	
}
