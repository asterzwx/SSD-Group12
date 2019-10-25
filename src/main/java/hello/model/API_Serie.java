package hello.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class API_Serie {
	
	public API_Serie() {}
	
	public API_Serie(String beginAt, Object description, String endAt, String fullName, Integer id,
			Integer leagueId, String modifiedAt, String name, String prizepool, Object season, String slug,
			Object winnerId, Object winnerType, Integer year) {
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
	public String beginAt;
	@SerializedName("description")
	@Expose
	public Object description;
	@SerializedName("end_at")
	@Expose
	public String endAt;
	@SerializedName("full_name")
	@Expose
	public String fullName;
	@SerializedName("id")
	@Expose
	public Integer id;
	@SerializedName("league_id")
	@Expose
	public Integer leagueId;
	@SerializedName("modified_at")
	@Expose
	public String modifiedAt;
	@SerializedName("name")
	@Expose
	public String name;
	@SerializedName("prizepool")
	@Expose
	public String prizepool;
	@SerializedName("season")
	@Expose
	public Object season;
	@SerializedName("slug")
	@Expose
	public String slug;
	@SerializedName("winner_id")
	@Expose
	public Object winnerId;
	@SerializedName("winner_type")
	@Expose
	public Object winnerType;
	@SerializedName("year")
	@Expose
	public Integer year;
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
	public String getEndAt() {
		return endAt;
	}
	public void setEndAt(String endAt) {
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
	public String getPrizepool() {
		return prizepool;
	}
	public void setPrizepool(String prizepool) {
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
