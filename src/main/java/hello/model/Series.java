package hello.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Series {
	public Series() {

	}

	public Series(String beginAt, Object description, Object endAt, String fullName, Integer id, Integer leagueId,
			String modifiedAt, String name, Object prizepool, Object season, String slug, Object winnerId,
			Object winnerType, Integer year) {
		super();
		this.beginAt = beginAt;
		this.description = description;
		this.endAt = endAt;
		this.fullName = fullName;
		this.id = id;
		this.leagueId = leagueId;
		this.modifiedAt = modifiedAt;
		this.name = name;
		this.prizepool = prizepool;
		this.season = season;
		this.slug = slug;
		this.winnerId = winnerId;
		this.winnerType = winnerType;
		this.year = year;
	}

	@SerializedName("begin_at")
	@Expose
	private String beginAt;

	@SerializedName("description")
	@Expose
	private Object description;

	@SerializedName("end_at")
	@Expose
	private Object endAt;

	@SerializedName("full_name")
	@Expose
	private String fullName;

	@SerializedName("id")
	@Expose
	private Integer id;

	@SerializedName("league_id")
	@Expose
	private Integer leagueId;

	@SerializedName("modified_at")
	@Expose
	private String modifiedAt;

	@SerializedName("name")
	@Expose
	private String name;

	@SerializedName("prizepool")
	@Expose
	private Object prizepool;

	@SerializedName("season")
	@Expose
	private Object season;

	@SerializedName("slug")
	@Expose
	private String slug;

	@SerializedName("winner_id")
	@Expose
	private Object winnerId;

	@SerializedName("winner_type")
	@Expose
	private Object winnerType;

	@SerializedName("year")
	@Expose
	private Integer year;

	public String getBeginAt() {
		return beginAt;
	}

	public void setBeginAt(String beginAt) {
		this.beginAt = beginAt;
	}

	public Object getDescription() {
		return description;
	}

	public void setDescription(Object description) {
		this.description = description;
	}

	public Object getEndAt() {
		return endAt;
	}

	public void setEndAt(Object endAt) {
		this.endAt = endAt;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(Integer leagueId) {
		this.leagueId = leagueId;
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

	public Object getPrizepool() {
		return prizepool;
	}

	public void setPrizepool(Object prizepool) {
		this.prizepool = prizepool;
	}

	public Object getSeason() {
		return season;
	}

	public void setSeason(Object season) {
		this.season = season;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public Object getWinnerId() {
		return winnerId;
	}

	public void setWinnerId(Object winnerId) {
		this.winnerId = winnerId;
	}

	public Object getWinnerType() {
		return winnerType;
	}

	public void setWinnerType(Object winnerType) {
		this.winnerType = winnerType;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

}
