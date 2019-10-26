package hello.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class API_League {
	public API_League() {}
	
	@SerializedName("begin_at")
	@Expose
	public String beginAt;
	@SerializedName("detailed_stats")
	@Expose
	public Boolean detailedStats;
	@SerializedName("draw")
	@Expose
	public Boolean draw;
	@SerializedName("end_at")
	@Expose
	public String endAt;
	@SerializedName("forfeit")
	@Expose
	public Boolean forfeit;
	@SerializedName("games")
	@Expose
	public List<API_Game> games = null;
	@SerializedName("id")
	@Expose
	public Integer id;
	@SerializedName("league")
	@Expose
	public API_League league;	
	@SerializedName("league_id")
	@Expose
	public Integer leagueId;
//	@SerializedName("live")
//	@Expose
//	public Live live;
	@SerializedName("live_url")
	@Expose
	public String liveUrl;
	@SerializedName("match_type")
	@Expose
	public String matchType;
	@SerializedName("modified_at")
	@Expose
	public String modifiedAt;
	@SerializedName("name")
	@Expose
	public String name;
	@SerializedName("number_of_games")
	@Expose
	public Integer numberOfGames;
	@SerializedName("opponents")
	@Expose
	public List<API_Opponent> opponents = null;
//	@SerializedName("results")
//	@Expose
//	public List<DotaAPI_Result_Past> results = null;
	@SerializedName("scheduled_at")
	@Expose
	public String scheduledAt;
	@SerializedName("serie")
	@Expose
	public API_Serie serie;
	@SerializedName("serie_id")
	@Expose
	public Integer serieId;
	@SerializedName("slug")
	@Expose
	public String slug;
	@SerializedName("status")
	@Expose
	public String status;
	@SerializedName("tournament")
	@Expose
	public API_Tournament tournament;
	@SerializedName("tournament_id")
	@Expose
	public Integer tournamentId;
	@SerializedName("videogame")
	@Expose
	public API_Videogame videogame;
//	@SerializedName("videogame_version")
//	@Expose
//	public Object videogameVersion;
	@SerializedName("winner")
	@Expose
	public API_Winner winner;
	@SerializedName("winner_id")
	@Expose
	public Integer winnerId;
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
	public Boolean getDraw() {
		return draw;
	}
	public void setDraw(Boolean draw) {
		this.draw = draw;
	}
	public String getEndAt() {
		return endAt;
	}
	public void setEndAt(String endAt) {
		this.endAt = endAt;
	}
	public Boolean getForfeit() {
		return forfeit;
	}
	public void setForfeit(Boolean forfeit) {
		this.forfeit = forfeit;
	}
	public List<API_Game> getGames() {
		return games;
	}
	public void setGames(List<API_Game> games) {
		this.games = games;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public API_League getLeague() {
		return league;
	}
	public void setLeague(API_League league) {
		this.league = league;
	}
	public Integer getLeagueId() {
		return leagueId;
	}
	public void setLeagueId(Integer leagueId) {
		this.leagueId = leagueId;
	}
	public String getLiveUrl() {
		return liveUrl;
	}
	public void setLiveUrl(String liveUrl) {
		this.liveUrl = liveUrl;
	}
	public String getMatchType() {
		return matchType;
	}
	public void setMatchType(String matchType) {
		this.matchType = matchType;
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
	public Integer getNumberOfGames() {
		return numberOfGames;
	}
	public void setNumberOfGames(Integer numberOfGames) {
		this.numberOfGames = numberOfGames;
	}
	public List<API_Opponent> getOpponents() {
		return opponents;
	}
	public void setOpponents(List<API_Opponent> opponents) {
		this.opponents = opponents;
	}
	public String getScheduledAt() {
		return scheduledAt;
	}
	public void setScheduledAt(String scheduledAt) {
		this.scheduledAt = scheduledAt;
	}
	public API_Serie getSerie() {
		return serie;
	}
	public void setSerie(API_Serie serie) {
		this.serie = serie;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public API_Tournament getTournament() {
		return tournament;
	}
	public void setTournament(API_Tournament tournament) {
		this.tournament = tournament;
	}
	public Integer getTournamentId() {
		return tournamentId;
	}
	public void setTournamentId(Integer tournamentId) {
		this.tournamentId = tournamentId;
	}
	public API_Videogame getVideogame() {
		return videogame;
	}
	public void setVideogame(API_Videogame videogame) {
		this.videogame = videogame;
	}
//	public Object getVideogameVersion() {
//		return videogameVersion;
//	}
//	public void setVideogameVersion(Object videogameVersion) {
//		this.videogameVersion = videogameVersion;
//	}
	public API_Winner getWinner() {
		return winner;
	}
	public void setWinner(API_Winner winner) {
		this.winner = winner;
	}
	public Integer getWinnerId() {
		return winnerId;
	}
	public void setWinnerId(Integer winnerId) {
		this.winnerId = winnerId;
	}
	
	
	

}
