package hello.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class API_Opponent {
	
	public API_Opponent() {}
	
	public API_Opponent(String acronym, Integer id, String imageUrl, String name, String slug) {
		super();
		this.acronym = acronym;
		this.id = id;
		this.imageUrl = imageUrl;
		this.name = name;
		this.slug = slug;
	}
	@SerializedName("acronym")
	@Expose
	public String acronym;
	@SerializedName("id")
	@Expose
	public Integer id;
	@SerializedName("image_url")
	@Expose
	public String imageUrl;
	@SerializedName("name")
	@Expose
	public String name;
	@SerializedName("slug")
	@Expose
	public String slug;
	public String getAcronym() {
		return acronym;
	}
	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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
