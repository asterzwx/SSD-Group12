package hello.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class API_Tournament {
	public API_Tournament() {}
	
	public API_Tournament(String beginAt, String endAt, Integer id, Integer leagueId, String modifiedAt,
			String name, Object prizepool, Integer serieId, String slug, Integer winnerId, Object winnerType) {
		super();
		this.beginAt = beginAt;
		this.endAt = endAt;
		this.id = id;
		this.leagueId = leagueId;
		this.modifiedAt = modifiedAt;
		this.name = name;
		this.prizepool = prizepool;
		this.serieId = serieId;
		this.slug = slug;
		this.winnerId = winnerId;
		this.winnerType = winnerType;
	}
	@SerializedName("begin_at")
	@Expose
	public String beginAt;
	@SerializedName("end_at")
	@Expose
	public String endAt;
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
	public Object prizepool;
	@SerializedName("serie_id")
	@Expose
	public Integer serieId;
	@SerializedName("slug")
	@Expose
	public String slug;
	@SerializedName("winner_id")
	@Expose
	public Integer winnerId;
	@SerializedName("winner_type")
	@Expose
	public Object winnerType;
	public String getBeginAt() {
		return beginAt;
	}
	public void setBeginAt(String beginAt) {
		this.beginAt = beginAt;
	}
	public String getEndAt() {
		return endAt;
	}
	public void setEndAt(String endAt) {
		this.endAt = endAt;
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
	public Integer getSerieId() {
		return serieId;
	}
	public void setSerieId(Integer serieId) {
		this.serieId = serieId;
	}
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public Integer getWinnerId() {
		return winnerId;
	}
	public void setWinnerId(Integer winnerId) {
		this.winnerId = winnerId;
	}
	public Object getWinnerType() {
		return winnerType;
	}
	public void setWinnerType(Object winnerType) {
		this.winnerType = winnerType;
	}
	
	

}
