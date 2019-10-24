package hello.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//@Entity
public class LeagueAPI {
	public LeagueAPI() {
	}

	public LeagueAPI(Integer id, String imageUrl, Boolean liveSupported, String modifiedAt, String name,
			List<Series> series, String slug, Object url, Videogame videogame) {
		super();
		this.id = id;
		this.imageUrl = imageUrl;
		this.liveSupported = liveSupported;
		this.modifiedAt = modifiedAt;
		this.name = name;
		this.series = series;
		this.slug = slug;
		this.url = url;
		this.videogame = videogame;
	}

	@SerializedName("id")
	@Expose
	private Integer id;

	@SerializedName("image_url")
	@Expose
	private String imageUrl;

	@SerializedName("live_supported")
	@Expose
	private Boolean liveSupported;

	@SerializedName("modified_at")
	@Expose
	private String modifiedAt;

	@SerializedName("name")
	@Expose
	private String name;

	@SerializedName("series")
	@Expose
	private List<Series> series = null;

	@SerializedName("slug")
	@Expose
	private String slug;

	@SerializedName("url")
	@Expose
	private Object url;

	@SerializedName("videogame")
	@Expose
	private Videogame videogame;

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

	public Boolean getLiveSupported() {
		return liveSupported;
	}

	public void setLiveSupported(Boolean liveSupported) {
		this.liveSupported = liveSupported;
	}

	public String getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(String modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Series> getSeries() {
		return series;
	}

	public void setSeries(List<Series> series) {
		this.series = series;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public Object getUrl() {
		return url;
	}

	public void setUrl(Object url) {
		this.url = url;
	}

	public Videogame getVideogame() {
		return videogame;
	}

	public void setVideogame(Videogame videogame) {
		this.videogame = videogame;
	}

}
