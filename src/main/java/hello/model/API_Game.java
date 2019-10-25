package hello.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class API_Game {
	public API_Game() {}
	
	public API_Game(String beginAt, Boolean detailedStats, String endAt, Boolean finished, Boolean forfeit,
			Integer id, Integer length, Integer matchId, Integer position, String status, Object videoUrl,
			API_Winner winner, String winnerType) {
		super();
		this.beginAt = beginAt;
		this.detailedStats = detailedStats;
		this.endAt = endAt;
		this.finished = finished;
		this.forfeit = forfeit;
		this.id = id;
		this.length = length;
		this.matchId = matchId;
		this.position = position;
		this.status = status;
		this.videoUrl = videoUrl;
		this.winner = winner;
		this.winnerType = winnerType;
	}
	@SerializedName("begin_at")
	@Expose
	public String beginAt;
	@SerializedName("detailed_stats")
	@Expose
	public Boolean detailedStats;
	@SerializedName("end_at")
	@Expose
	public String endAt;
	@SerializedName("finished")
	@Expose
	public Boolean finished;
	@SerializedName("forfeit")
	@Expose
	public Boolean forfeit;
	@SerializedName("id")
	@Expose
	public Integer id;
	@SerializedName("length")
	@Expose
	public Integer length;
	@SerializedName("match_id")
	@Expose
	public Integer matchId;
	@SerializedName("position")
	@Expose
	public Integer position;
	@SerializedName("status")
	@Expose
	public String status;
	@SerializedName("video_url")
	@Expose
	public Object videoUrl;
	@SerializedName("winner")
	@Expose
	public API_Winner winner;
	@SerializedName("winner_type")
	@Expose
	public String winnerType;
	public String getBeginAt() {
		return beginAt;
	}
	public void setBeginAt(String beginAt) {
		this.beginAt = beginAt;
	}
	public Boolean getDetailedStats() {
		return detailedStats;
	}
	public void setDetailedStats(Boolean detailedStats) {
		this.detailedStats = detailedStats;
	}
	public String getEndAt() {
		return endAt;
	}
	public void setEndAt(String endAt) {
		this.endAt = endAt;
	}
	public Boolean getFinished() {
		return finished;
	}
	public void setFinished(Boolean finished) {
		this.finished = finished;
	}
	public Boolean getForfeit() {
		return forfeit;
	}
	public void setForfeit(Boolean forfeit) {
		this.forfeit = forfeit;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public Integer getMatchId() {
		return matchId;
	}
	public void setMatchId(Integer matchId) {
		this.matchId = matchId;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Object getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(Object videoUrl) {
		this.videoUrl = videoUrl;
	}
	public API_Winner getWinner() {
		return winner;
	}
	public void setWinner(API_Winner winner) {
		this.winner = winner;
	}
	public String getWinnerType() {
		return winnerType;
	}
	public void setWinnerType(String winnerType) {
		this.winnerType = winnerType;
	}

}
